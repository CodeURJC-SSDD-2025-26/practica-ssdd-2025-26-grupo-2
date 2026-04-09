package com.ssdd.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Travel_pageController {
    @GetMapping("/travel_page")
	public String travel_page() {
		return "travel_page";
	}

}
