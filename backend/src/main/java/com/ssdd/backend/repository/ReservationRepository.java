package com.ssdd.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssdd.backend.model.Reservation;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUsuario(User usuario);
    List<Reservation> findByViaje(Travel viaje);
}