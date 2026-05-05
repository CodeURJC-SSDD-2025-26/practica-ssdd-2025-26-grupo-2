package com.ssdd.backend.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ssdd.backend.dto.ReviewDTO;
import com.ssdd.backend.dto.ReviewMapper;
import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.ReviewService;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.UserService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelService travelService;

    @GetMapping({ "", "/" })
    public ResponseEntity<Page<ReviewDTO>> getReviews(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Review> reviewPage = reviewService.findAll(pageable);
        return ResponseEntity.ok(reviewPage.map(mapper::toDTO));
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO, Principal principal) {

        User user = getAuthenticatedUser(principal);

        if (reviewDTO.viajeId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Travel id is required");
        }
        if (reviewDTO.comentario() == null || reviewDTO.comentario().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review comment cannot be empty");
        }
        if (reviewDTO.puntuacion() == null || reviewDTO.puntuacion() < 1 || reviewDTO.puntuacion() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review score must be between 1 and 5");
        }

        Travel travel = travelService.getTravelById(reviewDTO.viajeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));

        Review reviewEntity = new Review(reviewDTO.puntuacion(), reviewDTO.comentario().trim(), user, travel);
        Review createdReview = reviewService.save(reviewEntity);

        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId())
                .toUri();

        return ResponseEntity.created(location).body(mapper.toDTO(createdReview));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable Long id, Principal principal) {

        User user = getAuthenticatedUser(principal);
        Optional<Review> reviewOpt = reviewService.findById(id);

        if (reviewOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Review review = reviewOpt.get();

        if (!isAuthorized(review, user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this review");
        }

        reviewService.deleteById(id);
        return ResponseEntity.ok(mapper.toDTO(review));
    }

    private User getAuthenticatedUser(Principal principal) {

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in");
        }

        return userService.findByEmail(principal.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    private boolean isAuthorized(Review review, User user) {
        boolean isOwner = review.getAutor().getId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains("ADMIN");

        return isOwner || isAdmin;
    }
}
