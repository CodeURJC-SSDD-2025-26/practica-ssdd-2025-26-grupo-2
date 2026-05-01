package com.ssdd.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssdd.backend.security.jwt.JwtRequestFilter;
import com.ssdd.backend.security.jwt.JwtTokenProvider;
import com.ssdd.backend.security.jwt.UnauthorizedHandlerJwt;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .securityMatcher("/api/**")
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));

        http
                .authorizeHttpRequests(authorize -> authorize
                        // PRIVATE ENDPOINTS

                        // users
                        .requestMatchers(HttpMethod.POST, "/api/v1/users**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")

                        // PUBLIC ENDPOINTS
                        .anyRequest().permitAll());

        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT Token filter
        http.addFilterBefore(new JwtRequestFilter(userDetailsService, jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .authorizeHttpRequests(authorize -> authorize
                        // Public pages
                        .requestMatchers("/", "/index", "/signin", "/about", "/contact", "/viajes", "/contact.html",
                                "/forgot-password")
                        .permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                        .requestMatchers("/error403.html", "/error404.html", "/loginFailure").permitAll()
                        .requestMatchers("/", "/index", "/js/**", "/css/**", "/images/**", "/usuario/*/imagen")
                        .permitAll()

                        // Admin pages
                        .requestMatchers("/admin.html", "/admin/**", "/graphJourney.html", "/graphUser.html",
                                "/journeyManagement.html",
                                "/userManagement.html", "/addJourney.html", "/editJourney.html", "/userReservas.hmtl",
                                "/userReservas",
                                "/admin_user_reviews.html", "/editReservationPage.html")
                        .hasRole("ADMIN")

                        // Logged users
                        .requestMatchers("/userProfile.html", "/profile/**", "/profile").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/reservations/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/travels/*/reserve").hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/reviews/new").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/reviews").permitAll()

                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/signin")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureUrl("/loginFailure")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error403.html"));

        return http.build();
    }

}
