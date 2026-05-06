package com.ssdd.backend.controller.rest;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.ssdd.backend.dto.TravelDTO;
import com.ssdd.backend.dto.TravelMapper;
import com.ssdd.backend.dto.ImageDTO;
import com.ssdd.backend.dto.ImageMapper;
import com.ssdd.backend.model.Travel;
import com.ssdd.backend.model.Image;
import com.ssdd.backend.service.TravelService;
import com.ssdd.backend.service.ImageService;

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

    
    @GetMapping({  "/" })
    public ResponseEntity<Page<TravelDTO>> getTravels(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String daterange,
            @RequestParam(required = false) Integer travelers,
            @PageableDefault(size = 5) Pageable pageable) {
        
        
        Page<Travel> results;
        if (country != null || daterange != null || travelers != null) {
            results = travelService.searchTrips(country, daterange, travelers, pageable);
        } else {
            results = travelService.getAllTravels(pageable);
        }

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(results.map(travelMapper::toDTO));
    }
    
    @GetMapping("/{id}")
    public TravelDTO getTravel(@PathVariable long id) {
        Travel travel = travelService.getTravelById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));
        return travelMapper.toDTO(travel);
    }

    
    @PostMapping({ "", "/" })
    public ResponseEntity<TravelDTO> createTravel(@RequestBody TravelDTO travelDTO) {

        // --- MANUAL VALIDATION ---
        if (travelDTO.precio() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
        if (travelDTO.nombre() == null || travelDTO.nombre().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Travel name cannot be empty");
        }

        Travel travel = travelMapper.toEntity(travelDTO);
        travel = travelService.save(travel);
        TravelDTO savedTravelDTO = travelMapper.toDTO(travel);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedTravelDTO.id()).toUri();

        return ResponseEntity.created(location).body(savedTravelDTO);
    }

    @PutMapping("/{id}")
    public TravelDTO replaceTravel(@PathVariable long id, @RequestBody TravelDTO updatedTravelDTO) {

        if (updatedTravelDTO.precio() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
        if (updatedTravelDTO.nombre() == null || updatedTravelDTO.nombre().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Travel name cannot be empty");
        }

        Travel oldTravel = travelService.getTravelById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));

        Travel updatedTravel = travelMapper.toEntity(updatedTravelDTO);
        updatedTravel.setId(id);
        updatedTravel.setImagen(oldTravel.getImagen());
        updatedTravel.setReservas(oldTravel.getReservas());

        updatedTravel = travelService.save(updatedTravel);

        return travelMapper.toDTO(updatedTravel);
    }

    
    @DeleteMapping("/{id}")
    public TravelDTO deleteTravel(@PathVariable long id) {
        Travel travel = travelService.getTravelById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));

        travelService.delete(id);
        return travelMapper.toDTO(travel);
    }

    

    @PostMapping("/{id}/images/")
    public ResponseEntity<ImageDTO> createTravelImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {
        if (imageFile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image file cannot be empty");
        }

        Travel travel = travelService.getTravelById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));

        Image image = imageService.createImage(imageFile.getInputStream());

        if (travel.getImagen() != null) {
            imageService.deleteImage(travel.getImagen().getId());
        }

        travel.setImagen(image);
        travelService.save(travel);

        URI location = fromCurrentContextPath()
                .path("/api/v1/images/{imageId}/media")
                .buildAndExpand(image.getId())
                .toUri();

        return ResponseEntity.created(location).body(imageMapper.toDTO(image));
    }

    @DeleteMapping("/{travelId}/images/{imageId}")
    public ImageDTO deleteTravelImage(@PathVariable long travelId, @PathVariable long imageId) {
        Travel travel = travelService.getTravelById(travelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel not found"));

        Image image = travel.getImagen();
        if (image == null || !image.getId().equals(imageId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found for this travel");
        }

        travel.setImagen(null);
        travelService.save(travel);
        imageService.deleteImage(imageId);

        return imageMapper.toDTO(image);
    }
}