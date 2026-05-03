package com.ssdd.backend.controller.rest.User;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ssdd.backend.dto.UserDTO;
import com.ssdd.backend.dto.UserMapper;
import com.ssdd.backend.model.User;
import com.ssdd.backend.service.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> passwords,
            java.security.Principal principal) {

        User userToUpdate = userService.findById(id).orElseThrow();

       
        if (!userToUpdate.getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String newPassword = passwords.get("password");
        userService.updatePassword(id, newPassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/profile-image")
    public ResponseEntity<UserDTO> updateProfileImage(
            @PathVariable Long id,
            java.security.Principal principal,
            @RequestParam("file") MultipartFile file) throws IOException {

        User userToUpdate = userService.findById(id).orElseThrow();

       
        if (!userToUpdate.getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User updatedUser = userService.updateProfileImage(id, file);

        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }
    @PatchMapping("{id}/image")
    public ResponseEntity<UserDTO> resetImage(@PathVariable Long id){
        User toReset = userService.findById(id).orElseThrow();
        toReset.setImagenPerfil(null);
        userService.save(toReset);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/lists")
    public ResponseEntity<Page<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> usersPage = userService.findAll(pageable);
        Page<UserDTO> responsePage = usersPage.map(userMapper::toDTO);
        return ResponseEntity.ok(responsePage);
    }
}