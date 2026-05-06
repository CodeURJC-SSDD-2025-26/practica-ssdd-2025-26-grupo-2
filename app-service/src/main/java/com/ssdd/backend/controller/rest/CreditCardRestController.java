package com.ssdd.backend.controller.rest;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ssdd.backend.dto.CreditCardDTO;
import com.ssdd.backend.model.CreditCard;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.CreditCardService;
import com.ssdd.backend.service.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/credit-cards")
public class CreditCardRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping({"/"})
    public CreditCardDTO getCreditCard(Principal principal) {

        User user = getAuthenticatedUser(principal);

        CreditCard card = findUserCard(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card not found"));

        return toDTO(card, user);
    }

    @PostMapping({"/" })
    public ResponseEntity<CreditCardDTO> addCreditCard(
            @RequestBody CreditCardDTO creditCardDTO,
            Principal principal) {

        return saveCreditCard(creditCardDTO, principal);
    }

    @PutMapping({"/" })
    public ResponseEntity<CreditCardDTO> updateCreditCard(
            @RequestBody CreditCardDTO creditCardDTO,
            Principal principal) {

        return saveCreditCard(creditCardDTO, principal);
    }

    @DeleteMapping({"/"})
    public CreditCardDTO deleteCreditCard(Principal principal) {

        User user = getAuthenticatedUser(principal);

        CreditCard card = findUserCard(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card not found"));

        CreditCardDTO deletedCardDTO = toDTO(card, user);

        creditCardService.deleteFromUser(user);
        userService.save(user);

        return deletedCardDTO;
    }

    private ResponseEntity<CreditCardDTO> saveCreditCard(CreditCardDTO creditCardDTO, Principal principal) {

        validateCreditCard(creditCardDTO);

        User user = getAuthenticatedUser(principal);
        Optional<CreditCard> currentCard = findUserCard(user);
        boolean isNewCard = currentCard.isEmpty();

        CreditCard card = currentCard.orElseGet(CreditCard::new);
        card.setUser(user);
        card.setTitular(creditCardDTO.titular());
        card.setNumTarjeta(creditCardDTO.numTarjeta().replaceAll("\\s+", ""));
        card.setCaducidad(creditCardDTO.caducidad());
        card.setCvv(creditCardDTO.cvv());

        card = creditCardService.save(card);

        user.setTarjeta(card);
        userService.save(user);

        CreditCardDTO responseDTO = toDTO(card, user);

        if (!isNewCard) {
            return ResponseEntity.ok(responseDTO);
        }

        URI location = fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    private void validateCreditCard(CreditCardDTO creditCardDTO) {

        if (creditCardDTO == null ||
                !StringUtils.hasText(creditCardDTO.numTarjeta()) ||
                !StringUtils.hasText(creditCardDTO.titular()) ||
                !StringUtils.hasText(creditCardDTO.caducidad()) ||
                !StringUtils.hasText(creditCardDTO.cvv())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit card data is required");
        }
    }

    private User getAuthenticatedUser(Principal principal) {

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in");
        }

        return userService.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    private Optional<CreditCard> findUserCard(User user) {

        if (user.getTarjeta() != null) {
            return Optional.of(user.getTarjeta());
        }

        return creditCardService.findByUser(user);
    }

    private CreditCardDTO toDTO(CreditCard card, User user) {

        return new CreditCardDTO(
                card.getId(),
                user.getId(),
                card.getTitular(),
                card.getNumTarjeta(),
                card.getCvv(),
                card.getCaducidad(),
                card.getUltimosCuatro());
    }
}
