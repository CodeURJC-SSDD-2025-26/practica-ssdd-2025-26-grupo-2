package com.ssdd.backend.repository;

import com.ssdd.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByUserId(Long userId);

     Optional<CreditCard> findByUser(User user);
}