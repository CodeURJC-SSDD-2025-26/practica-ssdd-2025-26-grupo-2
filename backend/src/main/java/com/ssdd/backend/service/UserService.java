package com.ssdd.backend.service;

import com.ssdd.backend.model.Image;
import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private ImageService imageService;

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
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return userOpt;
            }
        }

        return Optional.empty();
    }

    @Transactional
    public void updatePassword(Long id, String newRawPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        user.setPassword(passwordEncoder.encode(newRawPassword));
        userRepository.save(user);
    }

    @Transactional
    public User updateProfileImage(Long id, MultipartFile file) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getImagenPerfil() == null) {
            // Si no tiene imagen, creamos una nueva entidad Image
            Image newImage = imageService.createImage(file.getInputStream());
            user.setImagenPerfil(newImage);
        } else {
            // Si ya tiene, usamos el método replace de tu ImageService
            imageService.replaceImageFile(user.getImagenPerfil().getId(), file.getInputStream());
        }

        return userRepository.save(user);
    }
    
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    
    @Transactional
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se pudo eliminar: El usuario con ID " + id + " no existe.");
        }
    }
}