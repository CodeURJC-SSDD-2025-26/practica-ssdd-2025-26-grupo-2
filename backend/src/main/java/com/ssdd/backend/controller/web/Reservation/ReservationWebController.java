package com.ssdd.backend.controller.Reservation;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssdd.backend.model.Reservation;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReservationService;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.UserService;

@Controller
public class ReservationWebController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelService travelService;

    @GetMapping({ "/profile", "/userProfile.html" })
    public String showUserProfile(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/signin";
        }

        User user = userService.findByEmail(principal.getName()).orElseThrow();
        List<Reservation> reservations = reservationService.findByUsuario(user);

        model.addAttribute("user", user);
        model.addAttribute("reservations", reservations);

        return "userProfile";
    }

    @PostMapping("/travels/{id}/reserve")
    public String createReservation(@PathVariable Long id,
            @RequestParam int numeroPersonas,
            Principal principal) {

        if (principal == null) {
            return "redirect:/signin";
        }

        if (numeroPersonas <= 0) {
            return "redirect:/userProfile.html";
        }

        User user = userService.findByEmail(principal.getName()).orElseThrow();
        Travel travel = travelService.getTravelById(id).orElseThrow();

        Reservation reservation = new Reservation();
        reservation.setUsuario(user);
        reservation.setViaje(travel);
        reservation.setNumeroPersonas(numeroPersonas);
        reservation.setFechaReserva(LocalDate.now());
        reservation.setPrecioTotal(travel.getPrecio() * numeroPersonas);
        reservation.setEstado(Reservation.Estado.CONFIRMADA);

        reservationService.save(reservation);

        return "redirect:/userProfile.html";
    }

    @GetMapping("/reservations/{id}")
    public String showReservation(@PathVariable Long id, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/signin";
        }

        Optional<Reservation> reservationOpt = reservationService.findById(id);
        if (reservationOpt.isEmpty()) {
            return "error/404";
        }

        Reservation reservation = reservationOpt.get();

        if (!isAuthorized(reservation, principal)) {
            return "error/403";
        }

        model.addAttribute("reservation", reservation);
        return "reservation";
    }

    @GetMapping("/reservations/{id}/edit")
    public String editReservationPage(@PathVariable Long id,
            @RequestParam(required = false) String returnTo,
            Principal principal,
            Model model) {

        if (principal == null) {
            return "redirect:/signin";
        }

        Optional<Reservation> reservationOpt = reservationService.findById(id);
        if (reservationOpt.isEmpty()) {
            return "error/404";
        }

        Reservation reservation = reservationOpt.get();

        if (!isAuthorized(reservation, principal)) {
            return "error/403";
        }

        model.addAttribute("reservation", reservation);
        model.addAttribute("returnTo", returnTo != null ? returnTo : "");
        return "editReservationPage";
    }

    @PostMapping("/reservations/{id}/edit")
    public String editReservationProcess(@PathVariable Long id,
            @RequestParam int numeroPersonas,
            @RequestParam(required = false) String returnTo,
            Principal principal) {

        if (principal == null) {
            return "redirect:/signin";
        }

        Optional<Reservation> reservationOpt = reservationService.findById(id);
        if (reservationOpt.isEmpty()) {
            return "error/404";
        }

        if (numeroPersonas <= 0) {
            if (returnTo != null && !returnTo.isBlank()) {
                return "redirect:/reservations/" + id + "/edit?returnTo=" + returnTo;
            }
            return "redirect:/reservations/" + id + "/edit";
        }

        Reservation reservation = reservationOpt.get();

        if (!isAuthorized(reservation, principal)) {
            return "error/403";
        }

        reservation.setNumeroPersonas(numeroPersonas);
        reservation.setPrecioTotal(reservation.getViaje().getPrecio() * numeroPersonas);
        reservationService.save(reservation);

        if (returnTo != null && !returnTo.isBlank()) {
            return "redirect:" + returnTo;
        }

        return "redirect:/userProfile.html";
    }

    @PostMapping("/reservations/{id}/cancel")
    public String cancelReservation(@PathVariable Long id,
            @RequestParam(required = false) String returnTo,
            Principal principal) {

        if (principal == null) {
            return "redirect:/signin";
        }

        Optional<Reservation> reservationOpt = reservationService.findById(id);
        if (reservationOpt.isEmpty()) {
            return "error/404";
        }

        Reservation reservation = reservationOpt.get();
        if (!isAuthorized(reservation, principal)) {
            return "error/403";
        }

        reservationService.deleteById(id);

        if (returnTo != null && !returnTo.isBlank()) {
            return "redirect:" + returnTo;
        }

        return "redirect:/userProfile.html";
    }

    private boolean isAuthorized(Reservation reservation, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow();

        boolean isOwner = reservation.getUsuario().getId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains("ADMIN");

        return isOwner || isAdmin;
    }
}