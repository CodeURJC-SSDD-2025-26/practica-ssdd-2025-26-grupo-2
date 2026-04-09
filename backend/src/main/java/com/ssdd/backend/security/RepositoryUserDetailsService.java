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

         User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> rol = new ArrayList<>();
        rol.add(new SimpleGrantedAuthority("ROLE_" + user.getRol().name()));

        return new org.springframework.security.core.userdetails.User(user.getNombre(), 
				user.getPassword(), rol);
    }


}
