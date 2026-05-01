package com.ssdd.backend.controller.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.service.TravelService;

@RestController
@RequestMapping("/api/v1/pages")
public class IndexRestController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TravelService travelService;

    @GetMapping({ "", "/", "/index" })
    public ResponseEntity<Map<String, Object>> showIndex() {

        List<Travel> todosLosViajes = travelService.getAllTravels();

        List<Map<String, Object>> primeros6 = todosLosViajes.stream()
                .limit(6)
                .map(this::toTravelResponse)
                .toList();

        String nombresAnimados = todosLosViajes.stream()
                .limit(6)
                .map(Travel::getNombre)
                .collect(Collectors.joining(","));

        List<Map<String, Object>> masPopulares = todosLosViajes.stream()
                .sorted((viaje1, viaje2) -> {
                    int reservas1 = (viaje1.getReservas() != null) ? viaje1.getReservas().size() : 0;
                    int reservas2 = (viaje2.getReservas() != null) ? viaje2.getReservas().size() : 0;
                    return Integer.compare(reservas2, reservas1);
                })
                .limit(4)
                .map(this::toTravelResponse)
                .toList();

        List<Map<String, Object>> reviews = reviewRepository.findAll().stream()
                .map(this::toReviewResponse)
                .toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("viajesNuevos", primeros6);
        response.put("viajesPopulares", masPopulares);
        response.put("nombresAnimados", nombresAnimados);
        response.put("reviews", reviews);

        return ResponseEntity.ok(response);
    }

    private Map<String, Object> toTravelResponse(Travel travel) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", travel.getId());
        response.put("nombre", travel.getNombre());
        response.put("descripcion", travel.getDescripcion());
        response.put("pais", travel.getPais());
        response.put("precio", travel.getPrecio());
        response.put("transporte", travel.getTransporte());
        response.put("alojamiento", travel.getAlojamiento());
        response.put("fechaInicio", travel.getFechaInicio());
        response.put("fechaFin", travel.getFechaFin());
        response.put("maxPlazas", travel.getMaxPlazas());
        response.put("numNoches", travel.getNumNoches());
        response.put("tieneImagen", travel.getTieneImagen());
        response.put("numReservas", travel.getReservas() != null ? travel.getReservas().size() : 0);
        return response;
    }

    private Map<String, Object> toReviewResponse(Review review) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", review.getId());
        response.put("puntuacion", review.getPuntuacion());
        response.put("comentario", review.getComentario());
        response.put("fecha", review.getFecha());
        response.put("autor", review.getAutor() != null ? review.getAutor().getNombre() : null);
        response.put("viaje", review.getViaje() != null ? review.getViaje().getNombre() : null);
        return response;
    }
}
