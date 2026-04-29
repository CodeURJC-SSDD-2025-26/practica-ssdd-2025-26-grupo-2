package com.ssdd.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.ssdd.backend.service.EmailService;

@Controller
public class ContactController {

	@Autowired
	private EmailService emailService;

	@GetMapping({ "/contact", "/contact.html" })
	public String contact(@RequestParam(required = false) String enviado, Model model) {
		if (enviado != null) {
			model.addAttribute("enviado", true);
		}
		return "contact";
	}

	@PostMapping("/contact")
	public String sendContact(@RequestParam String nombre,
			@RequestParam String apellidos,
			@RequestParam String email,
			@RequestParam String mensaje) {

		emailService.sendContactEmail(nombre, apellidos, email, mensaje);
		return "redirect:/contact?enviado=true";
	}
}
