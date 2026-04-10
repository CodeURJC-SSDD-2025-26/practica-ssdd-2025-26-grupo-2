package com.ssdd.backend.service;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    // 1. Obtener todos 
    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    // 2. Obtener uno por ID 
    public Optional<Travel> getTravelById(Long id) {
        return travelRepository.findById(id);
    }

    // 3. GUARDAR 
    // Usamos 'public Travel' para que nos devuelva el objeto guardado con su ID
    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    // 4. BORRAR 
    public void delete(long id) {
        travelRepository.deleteById(id);
    }
}