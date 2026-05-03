package com.ssdd.backend.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutRestController {

    @GetMapping("/api/v1/pages/about")
    public ResponseEntity<String> getAbout() {
        return ResponseEntity.ok(
                "Sobre nosotros\n\n" +
                "Diseñamos paquetes de viaje exclusivos que combinan transporte y alojamiento con el más alto estándar de calidad. " +
                "Tú solo tienes que decir bye bye, nosotros nos encargamos del resto.\n\n" +

                "Sobre nuestros viajes\n\n" +
                "Cada viaje es una experiencia pensada para que desconectes de la rutina y conectes con lo extraordinario. " +
                "Nuestros paquetes incluyen transporte y alojamiento seleccionados estratégicamente para garantizar comodidad, " +
                "ubicación privilegiada y una estancia sin complicaciones. " +
                "Todo está organizado para que vivas el destino con libertad, estilo y absoluta tranquilidad.\n\n" +

                "Nuestros paquetes están compuestos por: Aerolíneas, Cruceros, Trenes y Hoteles.\n\n" +

                "Equipo:\n" +
                "- Vanessa Fernandes Franco - Co-Founder & CEO\n" +
                "- María Abdallah El Lakkis - Co-Founder & CEO\n" +
                "- Yadira Reimúndez Queimadelos - Co-Founder & CEO\n" +
                "- Alejandro Hernández de Dios - Co-Founder & CEO"
        );
    }
}