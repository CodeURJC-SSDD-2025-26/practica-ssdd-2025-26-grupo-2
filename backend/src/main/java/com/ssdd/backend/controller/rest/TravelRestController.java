package com.ssdd.backend.controller.rest;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssdd.backend.dto.TravelDTO;
import com.ssdd.backend.dto.TravelMapper;
import com.ssdd.backend.dto.ImageDTO;
import com.ssdd.backend.dto.ImageMapper;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.Image;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.ImageService;

// Importaciones estáticas para acortar el código (Estilo Profesor)
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/travels")
public class TravelRestController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private ImageMapper imageMapper;

    // Equivale a tu antiguo GET /viajes (pero devuelve JSON)
    @GetMapping("/")
    public Collection<TravelDTO> getTravels() {
        return travelService.getAllTravels().stream()
                .map(travelMapper::toDTO)
                .toList();
    }

    // Equivale a tu antiguo GET /viajes/{id}
    @GetMapping("/{id}")
    public TravelDTO getTravel(@PathVariable long id) {
        // Usamos orElseThrow() para que lance error 404 si no existe
        Travel travel = travelService.getTravelById(id).orElseThrow(); 
        
        return travelMapper.toDTO(travel);
    }

    // Equivale a tu antiguo POST /nuevoviaje (pero SIN imagen)
    @PostMapping("/")
    public ResponseEntity<TravelDTO> createTravel(@RequestBody TravelDTO travelDTO) {

        Travel travel = travelMapper.toEntity(travelDTO);
        travel = travelService.save(travel); 
        TravelDTO savedTravelDTO = travelMapper.toDTO(travel);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedTravelDTO.id()).toUri();

        return ResponseEntity.created(location).body(savedTravelDTO);
    }

    // Equivale a tu antiguo POST /modificarviaje (en REST se usa PUT)
    @PutMapping("/{id}")
    public TravelDTO replaceTravel(@PathVariable long id, @RequestBody TravelDTO updatedTravelDTO) {

        Travel updatedTravel = travelMapper.toEntity(updatedTravelDTO);
        updatedTravel.setId(id);
        
        // Obtenemos el antiguo para no perder la imagen ni las reservas al actualizar
        Travel viajeAntiguo = travelService.getTravelById(id).orElseThrow();
        updatedTravel.setImagen(viajeAntiguo.getImagen());
        updatedTravel.setReservas(viajeAntiguo.getReservas());
        
        updatedTravel = travelService.save(updatedTravel);
        return travelMapper.toDTO(updatedTravel);
    }

    // Equivale a tu antiguo POST /borrarviaje/{id} (en REST se usa DELETE)
    @DeleteMapping("/{id}")
    public TravelDTO deleteTravel(@PathVariable long id) {
        Travel travel = travelService.getTravelById(id).orElseThrow();
        travelService.delete(id);
        return travelMapper.toDTO(travel);
    }

    // --- MÉTODOS PARA GESTIONAR LA IMAGEN  ---

    // Subir imagen separada
    @PostMapping("/{id}/images/")
    public ResponseEntity<ImageDTO> createTravelImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file cannot be empty");
        }

        Image image = imageService.createImage(imageFile.getInputStream());
        
        // Se lo asignamos al viaje
        Travel travel = travelService.getTravelById(id).orElseThrow();
        
        // Si ya tenía imagen, borramos la antigua para no dejar basura en la BBDD
        if (travel.getImagen() != null) {
            imageService.deleteImage(travel.getImagen().getId());
        }
        
        travel.setImagen(image);
        travelService.save(travel);

        // Genera la URL para que el cliente sepa dónde descargarla luego
        URI location = fromCurrentContextPath()
                .path("/api/v1/images/{imageId}/media")
                .buildAndExpand(image.getId())
                .toUri();

        return ResponseEntity.created(location).body(imageMapper.toDTO(image));
    }

    // Borrar la imagen de un viaje
    @DeleteMapping("/{travelId}/images/{imageId}")
    public ImageDTO deleteTravelImage(@PathVariable long travelId, @PathVariable long imageId) {

        Travel travel = travelService.getTravelById(travelId).orElseThrow();
        Image image = travel.getImagen();
        
        // Desvinculamos y borramos
        travel.setImagen(null);
        travelService.save(travel);
        imageService.deleteImage(imageId);

        return imageMapper.toDTO(image);
    }
}