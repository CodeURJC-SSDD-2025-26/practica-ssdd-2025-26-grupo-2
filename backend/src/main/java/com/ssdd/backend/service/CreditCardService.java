package com.ssdd.backend.service;

import com.ssdd.backend.model.CreditCard;
import com.ssdd.backend.model.User;

import com.ssdd.backend.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository repository;

    public CreditCard save(CreditCard card) {
        return repository.save(card);
    }

    public Optional<CreditCard> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<CreditCard> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(CreditCard card) {
        repository.delete(card);
    }

    public void deleteFromUser(User user) {
        CreditCard card = user.getTarjeta();
        if (card != null) {
            user.setTarjeta(null); 
            repository.delete(card); 
        }
    }
}