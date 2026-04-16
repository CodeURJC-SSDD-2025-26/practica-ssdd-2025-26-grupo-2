package com.ssdd.backend.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin.html")
	public String admin() {
		return "admin";
	}
    
	@GetMapping("/graphJourney.html")
    public String graphJourney() {
        return "graphJourney";
    }

    @GetMapping("/graphUser.html")
    public String graphUser() {
        return "graphUser";
    }
	
}
