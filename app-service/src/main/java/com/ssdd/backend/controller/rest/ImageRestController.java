package com.ssdd.backend.controller.rest;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import com.ssdd.backend.service.ImageService;


@RestController
@RequestMapping("/api/images")
public class ImageRestController {

    @Autowired
    private ImageService imageService;
    
    @GetMapping("/{id}/media")
    public ResponseEntity<Object> getImageFile(@PathVariable long id)
            throws SQLException, IOException {

        Resource imageFile = imageService.getImageFile(id);

        MediaType mediaType = MediaTypeFactory
                .getMediaType(imageFile)
                .orElse(MediaType.IMAGE_JPEG);

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(imageFile);
    }
}
