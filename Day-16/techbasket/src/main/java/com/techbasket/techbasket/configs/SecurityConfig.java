package com.techbasket.techbasket.configs;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JWTfilter jwtfilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity security)
      throws Exception {
    security
        .cors(corsConfig -> corsConfig.configurationSource(corsConfiger()))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(req -> req
            .requestMatchers("/userspecific/**")
            .authenticated()
            .requestMatchers("/**")
            .permitAll())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    return security.build();
  }

  @Bean
  CorsConfigurationSource corsConfiger() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
    config.setAllowCredentials(true);
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    config.setAllowedHeaders(Arrays.asList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
