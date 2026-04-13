package com.ssdd.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssdd.backend.model.Review; // IMPORTANTE: Asegúrate de tener este import
import com.ssdd.backend.model.Travel;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Spring generará la consulta automáticamente al ver el nombre del método
    List<Review> findByViajeId(Long viajeId);
    List<Review> findByViaje(Travel viaje);
}