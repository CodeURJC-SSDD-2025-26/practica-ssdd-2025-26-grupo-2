package com.ssdd.backend.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.model.CreditCard;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.UserService;
import com.ssdd.backend.service.CreditCardService; // Asumiendo que existe

@Controller
public class CreditCardController {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/addCard")
    public String addOrUpdateCard(
            @RequestParam String number,
            @RequestParam String owner,
            @RequestParam String expirationDate,
            @RequestParam String cvv,
            Principal principal) {

        String email = principal.getName();
        User user = userService.findByEmail(email).orElseThrow();

        CreditCard card = user.getTarjeta();

        if (card == null) {
            card = new CreditCard();
            card.setUser(user);
        }

        // 3. Actualizamos los datos
        card.setTitular(owner);
        card.setNumTarjeta(number.replaceAll("\\s+", ""));
        card.setCaducidad(expirationDate);
        card.setCvv(cvv);

        // 4. PERSISTENCIA: Usamos el servicio específico de tarjetas
        // Primero guardamos la tarjeta
        creditCardService.save(card);

        // Actualizamos la referencia en el usuario y guardamos el usuario
        user.setTarjeta(card);
        userService.save(user);

        return "redirect:/userProfile.html";
    }

    @PostMapping("/deleteCard")
    public String deleteCard(Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email).orElseThrow();

        if (user.getTarjeta() != null) {
            creditCardService.deleteFromUser(user);
            userService.save(user); // Guardamos al usuario sin la referencia a la tarjeta
        }

        return "redirect:/userProfile.html";
    }
}