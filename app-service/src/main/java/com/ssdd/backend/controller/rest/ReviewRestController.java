package com.ssdd.backend.controller.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import com.ssdd.backend.dto.ReviewDTO;
import com.ssdd.backend.dto.ReviewMapper;
import com.ssdd.backend.model.Review;
import com.ssdd.backend.service.ReviewService;
@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<ReviewDTO>> getReviews(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "5") int size) {
        
        // 1. Creamos el objeto de paginación
        Pageable pageable = PageRequest.of(page, size);
        
        // 2. Obtenemos la página de resultados del servicio
        Page<Review> reviewPage = reviewService.findAll(pageable);
        
        // 3. Convertimos la lista de entidades a DTOs usando el mapper
        List<ReviewDTO> dtos = mapper.toDTOs(reviewPage.getContent());
        
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // POST - Crear una nueva reseña
    @PostMapping("/")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        
        // 1. Usamos el mapper para convertir el DTO (record) en Entidad
        Review reviewEntity = mapper.toEntity(reviewDTO);
        
        // 2. El servicio se encarga de la lógica de guardado
        Review createdReview = reviewService.save(reviewEntity);

        if (createdReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // 3. Construimos la URL del nuevo recurso (estándar REST)
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdReview.getId())
                    .toUri();

            // 4. Devolvemos 201 Created con la URI y el objeto creado
            return ResponseEntity.created(location).body(mapper.toDTO(createdReview));
        }
    }

    // DELETE - Cancelar/Eliminar reseña
    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable Long id) {
        // 1. Buscamos la reseña primero para ver quién es el autor
        Optional<Review> reviewOpt = reviewService.findById(id);

        if (reviewOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Review review = reviewOpt.get();
        
        // 2. Lógica de seguridad: ¿Es Admin o es el autor?
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                          .stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // Comprobamos si el autor de la review coincide con el usuario logueado
        if (isAdmin || review.getAutor().getEmail().equals(currentUser)) {
            Review deletedReview = reviewService.deleteAndReturn(id);
            return ResponseEntity.ok(mapper.toDTO(deletedReview));
        } else {
            // 3. Si no tiene permiso, devolvemos 403 Forbidden
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}