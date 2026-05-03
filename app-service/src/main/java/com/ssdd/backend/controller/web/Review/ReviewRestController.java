package com.ssdd.backend.controller.web.Review;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // GET - Obtener reseñas (puedes añadir filtros si el Service los soporta)
    @GetMapping("/")
    public List<ReviewDTO> getReviews() {
        // Tu profesor devuelve directamente la lista mapeada a DTO
        return mapper.toDTOs(reviewService.findAll());
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
        // Seguimos el patrón del profe: el service devuelve el objeto borrado o null
        Review deletedReview = reviewService.deleteAndReturn(id);

        if (deletedReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(mapper.toDTO(deletedReview));
        }
    }
}