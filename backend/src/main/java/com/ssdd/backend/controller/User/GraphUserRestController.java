package com.ssdd.backend.controller.User;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssdd.backend.model.Reservation;
import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.service.ReservationService;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.TravelService;

@RestController
@RequestMapping("/api/graphs")
public class GraphUserRestController {
    @Autowired
    private TravelService travelService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/user")
    public Map<String, Object> graphUserData() {
        List<Reservation> reservations = reservationService.findAll();
        List<Travel> travels = travelService.getAllTravels();

        String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

        double[] gastos = new double[12];
        int[] numReservas = new int[12];

        Map<String, Integer> paises = new LinkedHashMap<>();
        for (Travel t : travels) {
            paises.put(t.getPais(), 0);
        }

        for (Reservation r : reservations) {
            int mes = r.getFechaReserva().getMonthValue() - 1;
            gastos[mes] += r.getPrecioTotal();
            numReservas[mes]++;
            String pais = r.getViaje().getPais();
            paises.put(pais, paises.getOrDefault(pais, 0) + 1);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("meses", Arrays.asList(meses));
        result.put("gastos", gastos);
        result.put("numReservas", numReservas);
        result.put("paisesLabels", new ArrayList<>(paises.keySet()));
        result.put("paisesData", new ArrayList<>(paises.values()));
        return result;
    }

    @GetMapping("/journey")
    public Map<String, Object> graphJourneyData() {
        List<Travel> travels = travelService.getAllTravels();

        List<String> labels = new ArrayList<>();
        List<Double> avgReviews = new ArrayList<>();
        List<Integer> totalReservations = new ArrayList<>();
        List<Integer> plazasLibres = new ArrayList<>();
        List<Integer> plazasOcupadas = new ArrayList<>();

        for (Travel t : travels) {
            labels.add(t.getNombre());

            List<Review> reviews = reviewService.findByViaje(t);
            double avg = reviews.stream()
                    .mapToInt(Review::getPuntuacion)
                    .average()
                    .orElse(0);
            avgReviews.add(Math.round(avg * 10.0) / 10.0);

            List<Reservation> reservations = reservationService.findByViaje(t);
            totalReservations.add(reservations.size());

            int ocupadas = reservations.stream()
                    .mapToInt(Reservation::getNumeroPersonas)
                    .sum();
            plazasOcupadas.add(ocupadas);
            plazasLibres.add(Math.max(0, t.getMaxPlazas() - ocupadas));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("labels", labels);
        result.put("avgReviews", avgReviews);
        result.put("totalReservations", totalReservations);
        result.put("plazasLibres", plazasLibres);
        result.put("plazasOcupadas", plazasOcupadas);
        return result;
    }
}