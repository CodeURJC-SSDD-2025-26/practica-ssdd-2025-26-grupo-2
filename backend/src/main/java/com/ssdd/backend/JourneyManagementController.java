package com.ssdd.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JourneyManagementController {
    @GetMapping("/journeyManagement.html")
	public String journeyManagement() {
		return "journeyManagement";
	}
}
