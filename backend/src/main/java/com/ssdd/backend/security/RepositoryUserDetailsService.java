

package com.ssdd.backend.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


import com.ssdd.backend.model.User;
import com.ssdd.backend.repository.UserRepository;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // ESTO DEBERÍA SALIR EN LA DEBUG CONSOLE NADA MÁS DARLE AL BOTÓN
        System.out.println(">>>> [PASO 1] El formulario ha llegado al servidor. Email recibido: " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println(">>>> [ERROR] El email [" + email + "] no existe en la base de datos.");
                    return new UsernameNotFoundException("User not found");
                });

        System.out.println(">>>> [PASO 2] Usuario encontrado en DB. Password (hash): " + user.getPassword());

        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : user.getRoles()) {
            System.out.println(">>>> [PASO 3] Cargando rol: ROLE_" + role);
            roles.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roles);
    }


}


