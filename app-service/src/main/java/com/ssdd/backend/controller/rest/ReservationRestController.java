package com.ssdd.backend.controller.rest;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import com.ssdd.backend.dto.ReservationDTO;
import com.ssdd.backend.dto.ReservationMapper;
import com.ssdd.backend.model.Reservation;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReservationService;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private ReservationMapper reservationMapper;

    @GetMapping("/")
    public Page<ReservationDTO> getReservations(Principal principal, Pageable pageable) {

        User user = getAuthenticatedUser(principal);

        if (user.getRoles().contains("ADMIN")) {
            return reservationService.findAll(pageable).map(reservationMapper::toDTO);
        }

        return reservationService.findByUsuario(user, pageable).map(reservationMapper::toDTO);
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservation(@PathVariable long id, Principal principal) {

        Reservation reservation = reservationService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (!isAuthorized(reservation, principal)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to access this reservation");
        }

        return reservationMapper.toDTO(reservation);
    }

    @PostMapping("/")
    public ResponseEntity<ReservationDTO> createReservation(
            @RequestBody ReservationDTO reservationDTO,
            Principal principal) {

        User user = getAuthenticatedUser(principal);

        if (reservationDTO.numeroPersonas() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of people must be greater than 0");
        }

        Travel travel = travelService.getTravelById(reservationDTO.viajeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));

        Reservation reservation = reservationMapper.toEntity(reservationDTO);

        reservation.setUsuario(user);
        reservation.setViaje(travel);
        reservation.setNumeroPersonas(reservationDTO.numeroPersonas());
        reservation.setFechaReserva(LocalDate.now());
        reservation.setPrecioTotal(travel.getPrecio() * reservationDTO.numeroPersonas());
        reservation.setEstado(Reservation.Estado.CONFIRMADA);

        reservation = reservationService.save(reservation);

        ReservationDTO newReservationDTO = reservationMapper.toDTO(reservation);

        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReservationDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(newReservationDTO);
    }

    @PutMapping("/{id}")
    public ReservationDTO replaceReservation(
            @PathVariable long id,
            @RequestBody ReservationDTO updatedReservationDTO,
            Principal principal) {

        Reservation reservation = reservationService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (!isAuthorized(reservation, principal)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to edit this reservation");
        }

        if (updatedReservationDTO.numeroPersonas() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of people must be greater than 0");
        }

        reservation.setNumeroPersonas(updatedReservationDTO.numeroPersonas());
        reservation.setPrecioTotal(reservation.getViaje().getPrecio() * updatedReservationDTO.numeroPersonas());

        reservation = reservationService.save(reservation);

        return reservationMapper.toDTO(reservation);
    }

    @DeleteMapping("/{id}")
    public ReservationDTO deleteReservation(@PathVariable long id, Principal principal) {

        Reservation reservation = reservationService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        if (!isAuthorized(reservation, principal)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this reservation");
        }

        reservationService.deleteById(id);

        return reservationMapper.toDTO(reservation);
    }

    private User getAuthenticatedUser(Principal principal) {

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in");
        }

        return userService.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    private boolean isAuthorized(Reservation reservation, Principal principal) {

        User user = getAuthenticatedUser(principal);

        boolean isOwner = reservation.getUsuario().getId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains("ADMIN");

        return isOwner || isAdmin;
    }
}
