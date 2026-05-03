package com.ssdd.backend.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.ssdd.backend.dto.EmailRequestDTO;
import com.ssdd.backend.service.UtilityEmailClient;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class ContactController {

	@Autowired
	private UtilityEmailClient utilityEmailClient;

	@Value("${contact.email.to}")
	private String contactEmailTo;

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

		EmailRequestDTO emailRequest = new EmailRequestDTO(
				contactEmailTo,
				"Nuevo mensaje de contacto - " + nombre + " " + apellidos,
				"Nombre: " + nombre + " " + apellidos + "\n" +
						"Email: " + email + "\n\n" +
						"Mensaje:\n" + mensaje);

		utilityEmailClient.sendEmail(emailRequest);

		return "redirect:/contact?enviado=true";
	}
}
