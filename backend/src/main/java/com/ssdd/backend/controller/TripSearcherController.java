package com.ssdd.backend.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.service.TravelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TripSearcherController {
    @Autowired
    private TravelService travelService;

    @PostMapping("/indexSearch")
    public String indexSearch(Model model, 
                              @RequestParam String country, 
                              @RequestParam String daterange, 
                              @RequestParam Integer travelers) {
        
        List<Travel> results = travelService.searchTrips(country, daterange, travelers);
        if(results == null){
            return "index";
        }
        model.addAttribute("viajes", results);
        return "travel_page"; 
    }

}
