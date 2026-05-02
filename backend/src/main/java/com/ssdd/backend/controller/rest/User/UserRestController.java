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

        User user = userMapper.toEntity(userDTO);
        if (user.getAcceptTerms() == null || !user.getAcceptTerms()) {
            throw new IllegalArgumentException("No se puede registrar un usuario sin aceptar los términos.");
        }

        User newUser = userService.registerUser(user.getNombre(), user.getEmail(), user.getPassword());

        UserDTO responseDTO = userMapper.toDTO(newUser);

        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }
}