package com.ssdd.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Travel_pageController {
    @GetMapping("/travel_page.html")
	public String travel_page() {
		return "travel_page";
	}

}
