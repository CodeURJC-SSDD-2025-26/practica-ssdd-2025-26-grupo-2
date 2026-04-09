package com.ssdd.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphUserController {
    @GetMapping("/graphUser")
	public String graphUser() {
		return "graphUser";
	}
}
