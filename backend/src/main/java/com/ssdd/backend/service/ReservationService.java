package com.ssdd.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdd.backend.model.Reservation;
import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findByUsuario(User user) {
        return reservationRepository.findByUsuario(user);
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
