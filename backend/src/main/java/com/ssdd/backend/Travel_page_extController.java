package com.ssdd.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Travel_page_extController {
    @GetMapping("/travel_page_ext.html")
	public String travel_page_ext() {
		return "travel_page_ext";
	}
}
