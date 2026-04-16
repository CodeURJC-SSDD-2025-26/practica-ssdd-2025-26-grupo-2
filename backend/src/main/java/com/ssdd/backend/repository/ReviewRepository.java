package com.ssdd.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssdd.backend.model.Review; 
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByViajeId(Long viajeId);
    List<Review> findByViaje(Travel viaje);
    List<Review> findByAutor(User autor);
}