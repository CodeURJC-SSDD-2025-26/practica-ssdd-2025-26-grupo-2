package com.ssdd.backend.service;

import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String nombre, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User(nombre, email.trim(), passwordEncoder.encode(password), "USER");

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email.trim());
    }

    public Optional<User> login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Comparamos la contraseña en bruto con la encriptada de la DB
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return userOpt;
            }
        }

        return Optional.empty();
    }
}