package com.ssdd.backend.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    
    public Page<Travel> getAllTravels(Pageable pageable) {
        return travelRepository.findAll(pageable);
    }

    public Optional<Travel> getTravelById(long id) {
        return travelRepository.findById(id);
    }

    public Page<Travel> searchTrips(String country, String daterange, Integer travelers, Pageable pageable) {

        
        String[] dates = daterange.split("/");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(dates[0], formatter);
        LocalDate end = LocalDate.parse(dates[1], formatter);

        return travelRepository.findCustomTrips(country, travelers, start, end, pageable);
    }

    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    public void delete(long id) {
        travelRepository.deleteById(id);
    }
}