package com.ssdd.backend.controller.rest.User;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssdd.backend.dto.UserDTO;
import com.ssdd.backend.dto.UserMapper;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/users") 
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        
        // 1. Convertimos el Record DTO a Entidad
        User user = userMapper.toEntity(userDTO);
        
        // 2. Llamamos al servicio que cifrará la contraseña 
        User newUser = userService.registerUser(user.getNombre(), user.getEmail(), user.getPassword());
        
        // 3. Convertimos la entidad guardada de vuelta a Record DTO
        UserDTO responseDTO = userMapper.toDTO(newUser);

        // 4. Construimos la URI: /api/v1/users/{id}
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }
}