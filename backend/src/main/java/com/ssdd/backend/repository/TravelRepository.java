package com.ssdd.backend.repository;

import com.ssdd.backend.model.Travel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    @Query("SELECT t FROM Travel t WHERE t.pais = :country " +
       "AND t.maxPlazas >= :travelers " +
       "AND (t.fechaInicio >= :start AND t.fechaFin <= :end)")
List<Travel> findCustomTrips(String country, int travelers, LocalDate start, LocalDate end);
}
