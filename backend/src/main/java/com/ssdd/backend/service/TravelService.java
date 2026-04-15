package com.ssdd.backend.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.TravelRepository;




@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }
 
    public Optional<Travel> getTravelById(Long id) {
        return travelRepository.findById(id);
    }

    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    
    public void delete(long id) {
        travelRepository.deleteById(id);
    }
    public List<Travel> searchTrips(String country, String daterange, Integer travelers) {
        if (daterange == null || !daterange.contains(" - ") || country == null || travelers == null) {
            return null; 
        }

        String[] dates = daterange.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        LocalDate startDate = LocalDate.parse(dates[0], formatter);
        LocalDate endDate = LocalDate.parse(dates[1], formatter);

        return travelRepository.findCustomTrips(country, travelers, startDate, endDate);
    }
}