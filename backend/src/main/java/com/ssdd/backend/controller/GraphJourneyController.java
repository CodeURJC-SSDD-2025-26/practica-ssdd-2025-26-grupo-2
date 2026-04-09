package com.ssdd.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphJourneyController {
    @GetMapping("/grapghJourney")
	public String grapghJourney() {
		return "grapghJourney";
	}
}
