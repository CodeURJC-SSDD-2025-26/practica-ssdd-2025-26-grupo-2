package com.ssdd.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ssdd.backend.model.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    Page<Travel> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    
    Page<Travel> findAll(Pageable pageable);
}
