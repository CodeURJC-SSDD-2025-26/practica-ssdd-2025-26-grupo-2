package com.ssdd.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {
    @GetMapping("/signin.html")
	public String signin() {
		return "signin";
	}
}
