package com.ssdd.backend.service;

import com.ssdd.backend.model.*;
import com.ssdd.backend.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ImageService imageService;

    @PostConstruct
    @Transactional // <-- AÑADIDO PARA EVITAR ERRORES DE SESIÓN/RELACIONES
    public void init() throws IOException {

        if (userRepository.count() == 0) {

            // 1. CREAR USUARIOS
            User admin = new User("Admin", "admin@byebye.com", "admin123", User.Rol.ADMIN);
            User user1 = new User("Juan Pérez", "juan@gmail.com", "pass123", User.Rol.USER);
            User user2 = new User("María López", "maria@gmail.com", "pass123", User.Rol.USER);

            try {
                setUserImage(admin, "/sample_images/profile_image.png");
                setUserImage(user1, "/sample_images/profile_image.png");
                setUserImage(user2, "/sample_images/profile_image.png");
            } catch (Exception e) {
                System.out.println("Aviso: No se encontraron las imágenes de perfil. Se crearán sin foto.");
            }

            userRepository.save(admin);
            userRepository.save(user1);
            userRepository.save(user2);

            // 2. CREAR VIAJES DE EJEMPLO
            Travel viaje1 = new Travel("Escapada a París", "París, Francia", 450.0);
            viaje1.setDescripcion("Disfruta de un fin de semana romántico cerca de la Torre Eiffel.");
            viaje1.setFechaInicio(LocalDate.now().plusDays(10));
            viaje1.setFechaFin(LocalDate.now().plusDays(14));
            viaje1.setMaxPlazas(20);
            viaje1.setTipo("Romántico");

            Travel viaje2 = new Travel("Aventura en los Alpes", "Alpes Suizos", 850.0);
            viaje2.setDescripcion("Semana de esquí y snowboard para niveles avanzados.");
            viaje2.setFechaInicio(LocalDate.now().plusDays(30));
            viaje2.setFechaFin(LocalDate.now().plusDays(37));
            viaje2.setMaxPlazas(15);
            viaje2.setTipo("Aventura");

            try {
                setTravelImage(viaje1, "static/images/paris.jpg");
                setTravelImage(viaje2, "static/images/alpes.jpg");
            } catch (Exception e) {
                System.out.println("Aviso: No se encontraron las imágenes de los viajes. Se crearán sin foto.");
            }

            travelRepository.save(viaje1);
            travelRepository.save(viaje2);

            // 3. CREAR RESERVAS
            Reservation reserva1 = new Reservation(user1, viaje1, 2, LocalDate.now(), 900.0,
                    Reservation.Estado.CONFIRMADA);
            Reservation reserva2 = new Reservation(user2, viaje2, 1, LocalDate.now(), 850.0,
                    Reservation.Estado.CONFIRMADA);

            reservationRepository.save(reserva1);
            reservationRepository.save(reserva2);

            // 4. CREAR RESEÑAS
            Review review1 = new Review(5, "Viaje inolvidable, todo salió a la perfección.", user1);
            Review review2 = new Review(4, "Muy buena experiencia, aunque hizo un poco de frío.", user2);

            reviewRepository.save(review1);
            reviewRepository.save(review2);

            System.out.println("--- DATOS DE EJEMPLO CARGADOS CORRECTAMENTE ---");
        }
    }

    public void setTravelImage(Travel travel, String classpathResource) throws IOException {
        Resource imageResource = new ClassPathResource(classpathResource);
        if (imageResource.exists()) {
            Image createdImage = imageService.createImage(imageResource.getInputStream());
            travel.setImagen(createdImage);
        }
    }

    public void setUserImage(User user, String classpathResource) throws IOException {
        Resource imageResource = new ClassPathResource(classpathResource);
        if (imageResource.exists()) {
            Image createdImage = imageService.createImage(imageResource.getInputStream());
            user.setImagenPerfil(createdImage);
        }
    }
}