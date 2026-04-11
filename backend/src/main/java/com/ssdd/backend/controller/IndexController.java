package com.ssdd.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {
    @GetMapping("/")
	public String indexReturn(Model model) {
		return "index";
	}
	@GetMapping("/index.html")
	public String index(Model model) {
		return "index";
	}

}
