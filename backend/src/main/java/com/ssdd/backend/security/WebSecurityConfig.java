
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
                .requestMatchers("/", "/index", "/signin", "/about", "/contact", "/viajes", "/contact.html").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .requestMatchers("/error403.html", "/error404.html").permitAll()
                .requestMatchers("/", "/index", "/js/**", "/css/**", "/images/**", "/usuario/*/imagen").permitAll() 

                

                // Admin pages
                .requestMatchers("/admin.html", "/admin/**", "/graphJourney.html", "/graphUser.html", "/journeyManagement.html", 
                         "/userManagement.html", "/addJourney.html", "/editJourney.html").hasRole("ADMIN") 

                // Logged users
                .requestMatchers("/userProfile.html", "/profile/**", "/profile").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/reservations/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/travels/*/reserve").hasAnyRole("USER", "ADMIN")

                .requestMatchers("/reviews/new").hasAnyRole("USER", "ADMIN") 
                .requestMatchers("/reviews").permitAll()

                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/loginFailure")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)     
                .deleteCookies("JSESSIONID")     
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/error403.html")
            );


        return http.build();
    }

    
}


