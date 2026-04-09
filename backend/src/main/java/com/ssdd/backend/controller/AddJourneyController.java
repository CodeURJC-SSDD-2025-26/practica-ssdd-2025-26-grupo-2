package com.ssdd.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddJourneyController {
    @GetMapping("/addJourney.html")
	public String addJourney() {
		return "addJourney";
	}
}
