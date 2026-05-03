package com.ssdd.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.ReviewRepository;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Page<Review> findByViaje(Travel travel, Pageable pageable) {
        return reviewRepository.findByViaje(travel, pageable);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findByAutor(User autor) {
        return reviewRepository.findByAutor(autor);
    }

    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }

    public Optional<Review> findById(long id) {
        return reviewRepository.findById(id);
    }

    public Review deleteAndReturn(Long id) {
        // 1. Buscamos la reseña antes de borrarla
        Optional<Review> review = reviewRepository.findById(id);
        
        if (review.isPresent()) {
            // 2. Si existe, la eliminamos de la base de datos
            reviewRepository.deleteById(id);
            
            // 3. Devolvemos el objeto que acabamos de borrar
            return review.get();
        }
        
        // 4. Si no existía, devolvemos null
        return null;
    }

    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public List<Review> findAllByViaje(Travel travel) {
        return reviewRepository.findByViaje(travel);
    }
}