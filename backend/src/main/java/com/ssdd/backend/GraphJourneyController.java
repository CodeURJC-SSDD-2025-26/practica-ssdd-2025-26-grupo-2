package com.ssdd.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphJourneyController {
    @GetMapping("/grapghJourney.html")
	public String grapghJourney() {
		return "grapghJourney";
	}
}
