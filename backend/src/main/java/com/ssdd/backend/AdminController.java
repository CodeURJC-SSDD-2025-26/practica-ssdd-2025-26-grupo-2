package com.ssdd.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin.html")
	public String admin() {
		return "admin";
	}
    
}
