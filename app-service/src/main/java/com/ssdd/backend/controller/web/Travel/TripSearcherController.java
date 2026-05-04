package com.ssdd.backend.controller.web.Travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssdd.backend.model.Travel;
import com.ssdd.backend.service.TravelService;

@Controller
public class TripSearcherController {

    @Autowired
    private TravelService travelService;

    @PostMapping("/indexSearch")
    public String indexSearch(Model model, 
                              @RequestParam String country, 
                              @RequestParam String daterange, 
                              @RequestParam Integer travelers,
                              @PageableDefault(size = 5) Pageable pageable) {
        
        // Llamada al servicio con el parámetro pageable
        Page<Travel> resultsPage = travelService.searchTrips(country, daterange, travelers, pageable);
        
        if(resultsPage.isEmpty()){
            model.addAttribute("mensaje", "No se han encontrado resultados");
            return "index";
        }

        // 'viajes' es la lista de elementos que usa tu th:each en el HTML
        model.addAttribute("viajes", resultsPage.getContent());
        
        // 'travelPage' contiene la información de paginación (total de páginas, actual, etc.)
        model.addAttribute("travelPage", resultsPage); 
        
        return "travel_page"; 
    }
}