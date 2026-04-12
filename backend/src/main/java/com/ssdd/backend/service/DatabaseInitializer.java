package com.ssdd.backend.service;

import com.ssdd.backend.model.*;
import com.ssdd.backend.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;


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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional // <-- AÑADIDO PARA EVITAR ERRORES DE SESIÓN/RELACIONES
    public void init() throws IOException {

        if (userRepository.count() == 0) {

            // 1. CREAR USUARIOS
            User admin = new User("Admin", "admin@byebye.com", passwordEncoder.encode("admin123"), "ADMIN");
            User user1 = new User("Carla Llorente", "carla@gmail.com",  passwordEncoder.encode("pass123"), "USER");
            User user2 = new User("Lucas Torres", "lucas@gmail.com",  passwordEncoder.encode("pass123"), "USER");
            User user3 = new User("Lucía Gómez", "lucia@gmail.com",  passwordEncoder.encode("pass123"), "USER");
            try {
               
                setUserImage(admin, "/sample_images/person_4.jpg");
                setUserImage(user1, "/sample_images/default_profile.jpg");
                setUserImage(user2, "/sample_images/person_3.jpg");
                setUserImage(user3, "/sample_images/person_2.jpg");
            } catch (Exception e) {
                System.out.println("Aviso: No se encontraron algunas imágenes de perfil. Se crearán sin foto.");
            }

            userRepository.save(admin);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

           // 2. CREAR VIAJES 
            
            Travel viaje1 = new Travel(
                "San Francisco", 
                "Estados Unidos", 
                1250.00, 
                "Vuelo internacional con 1 escala", 
                "Hotel 4 estrellas cerca del Golden Gate", 
                25, 
                7
            );
            viaje1.setDescripcion("Recorre las empinadas calles de San Francisco, visita Alcatraz y pasea en bicicleta por el icónico puente rojo.");
            viaje1.setFechaInicio(LocalDate.now().plusDays(20));
            viaje1.setFechaFin(LocalDate.now().plusDays(27));

            Travel viaje2 = new Travel(
                "Paris", 
                "Francia", 
                300.00, 
                "Vuelo directo", 
                "Hotel boutique en el centro", 
                30, 
                3
            );
            viaje2.setDescripcion("Una escapada romántica inolvidable. Disfruta de la Torre Eiffel, el museo del Louvre y la mejor gastronomía.");
            viaje2.setFechaInicio(LocalDate.now().plusDays(10));
            viaje2.setFechaFin(LocalDate.now().plusDays(13));

            Travel viaje3 = new Travel(
                "Putrajaya", 
                "Malasia", 
                750.00, 
                "Vuelo largo radio", 
                "Resort de lujo con vistas al lago", 
                15, 
                10
            );
            viaje3.setDescripcion("Descubre la majestuosa arquitectura y los tranquilos lagos de la capital administrativa de Malasia.");
            viaje3.setFechaInicio(LocalDate.now().plusDays(40));
            viaje3.setFechaFin(LocalDate.now().plusDays(50));

            Travel viaje4 = new Travel(
                "Bora Bora", 
                "Polinesia Francesa", 
                520.00, 
                "Vuelo + Traslado en hidroavión", 
                "Bungalow sobre el agua", 
                10, 
                6
            );
            viaje4.setDescripcion("El paraíso en la tierra. Desconecta en aguas cristalinas rodeado de arrecifes de coral y naturaleza exuberante.");
            viaje4.setFechaInicio(LocalDate.now().plusDays(60));
            viaje4.setFechaFin(LocalDate.now().plusDays(66));

            Travel viaje5 = new Travel(
                "Nairobi", 
                "Kenia", 
                760.00, 
                "Vuelo directo", 
                "Lodge ecológico en la sabana", 
                12, 
                7
            );
            viaje5.setDescripcion("Aventura salvaje. Safaris diarios para observar elefantes, leones y jirafas en su hábitat natural.");
            viaje5.setFechaInicio(LocalDate.now().plusDays(25));
            viaje5.setFechaFin(LocalDate.now().plusDays(32));

            Travel viaje6 = new Travel(
                "Sidney", 
                "Australia", 
                820.00, 
                "Vuelo con 2 escalas", 
                "Apartamento céntrico con vistas a la bahía", 
                20, 
                12
            );
            viaje6.setDescripcion("Conoce la famosa Ópera, relájate en Bondi Beach y descubre la vibrante cultura australiana.");
            viaje6.setFechaInicio(LocalDate.now().plusDays(90));
            viaje6.setFechaFin(LocalDate.now().plusDays(102));

            Travel viaje7 = new Travel(
                "Machu Picchu", 
                "Perú", 
                955.00, 
                "Vuelo + Tren panorámico", 
                "Hostal tradicional andino", 
                15, 
                8
            );
            viaje7.setDescripcion("Explora la ciudad perdida de los Incas. Una ruta mágica llena de historia, cultura y paisajes impresionantes.");
            viaje7.setFechaInicio(LocalDate.now().plusDays(45));
            viaje7.setFechaFin(LocalDate.now().plusDays(53));

            Travel viaje8 = new Travel(
                "Bergen", 
                "Noruega", 
                568.00, 
                "Vuelo directo", 
                "Cabaña tradicional de madera", 
                18, 
                5
            );
            viaje8.setDescripcion("La puerta a los fiordos noruegos. Piérdete por el barrio de Bryggen y maravíllate con la naturaleza.");
            viaje8.setFechaInicio(LocalDate.now().plusDays(15));
            viaje8.setFechaFin(LocalDate.now().plusDays(20));

            
           // ASIGNAR IMÁGENES
            try {
                // Ahora apuntamos a la carpeta sample_images
                setTravelImage(viaje1, "sample_images/San-Francisco.jpg");
                setTravelImage(viaje2, "sample_images/Paris.jpg");
                setTravelImage(viaje3, "sample_images/Putrajaya.jpg");
                setTravelImage(viaje4, "sample_images/Bora-Bora.jpg");
                setTravelImage(viaje5, "sample_images/Nairobi.jpg"); 
                setTravelImage(viaje6, "sample_images/Sidney.jpg");
                setTravelImage(viaje7, "sample_images/Machu-Piccu.jpg"); 
                setTravelImage(viaje8, "sample_images/Bergen.jpg");
                
            } catch (Exception e) {
                System.out.println("Aviso: No se encontraron algunas imágenes de los viajes. Se crearán sin foto.");
            }

            // GUARDAR EN BASE DE DATOS
            travelRepository.save(viaje1);
            travelRepository.save(viaje2);
            travelRepository.save(viaje3);
            travelRepository.save(viaje4);
            travelRepository.save(viaje5);
            travelRepository.save(viaje6);
            travelRepository.save(viaje7);
            travelRepository.save(viaje8);
            
            // 3. CREAR RESERVAS
            Reservation reserva1 = new Reservation(user1, viaje1, 2, LocalDate.now(), 900.0,
                    Reservation.Estado.CONFIRMADA);
            Reservation reserva2 = new Reservation(user2, viaje2, 1, LocalDate.now(), 850.0,
                    Reservation.Estado.CONFIRMADA);

            reservationRepository.save(reserva1);
            reservationRepository.save(reserva2);

            // 4. CREAR RESEÑAS
            Review review1 = new Review(5, "Viaje increíble, todo muy bien organizado y el hotel espectacular.", user1);
            Review review2 = new Review(4, "Muy buena experiencia. El transporte fue cómodo y puntual.", user2);
            Review review3 = new Review(5, "Muy buena experiencia... y el itinerario perfecto.", user3); // Lucía (Texto recortado para la parte clara)

            reviewRepository.save(review1);
            reviewRepository.save(review2);
            reviewRepository.save(review3);
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