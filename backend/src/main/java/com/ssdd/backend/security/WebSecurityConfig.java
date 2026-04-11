
package com.ssdd.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {
    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
            .authorizeHttpRequests(authorize -> authorize
                // Public pages
                .requestMatchers("/", "/index", "/about", "/signin", "/contact").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .requestMatchers("/error403.html", "/error404.html").permitAll()

                // Admin pages
                //.requestMatchers("/admin", "/graphJourney", "/graphUser", "/journeyManagement", 
                  //                              "/UserManagement", "/addJourney").hasRole("ADMIN")
                .requestMatchers("/admin.html", "/admin/**").hasRole("ADMIN") 

                // Logged users
                .requestMatchers("/userProfile.html", "/profile/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/reservation/**").hasAnyRole("USER", "ADMIN")
                

                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/loginFailure")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/error403.html")
            );

        return http.build();
    }

    
}


