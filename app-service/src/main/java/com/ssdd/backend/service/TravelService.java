package com.ssdd.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.TravelRepository;
import java.util.Optional;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    // Obtener todos los viajes paginados
    public Page<Travel> getAllTravels(Pageable pageable) {
        // Usamos el nombre exacto de la variable de arriba
        return travelRepository.findAll(pageable);
    }

    public Optional<Travel> getTravelById(long id) {
        return travelRepository.findById(id);
    }

    // El buscador corregido para que no de error
    public Page<Travel> searchTrips(String country, String daterange, Integer travelers, Pageable pageable) {
        // Por ahora devolvemos todos paginados para que compile 
        // Luego puedes cambiarlo por tu lógica de filtros
        return travelRepository.findAll(pageable);
    }

    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    public void delete(long id) {
        travelRepository.deleteById(id);
    }
}