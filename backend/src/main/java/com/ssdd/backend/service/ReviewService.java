package com.ssdd.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdd.backend.model.Review;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.repository.ReviewRepository;
import com.ssdd.backend.model.User;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public List<Review> findByViaje(Travel travel) {
        return reviewRepository.findByViaje(travel);
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
}
