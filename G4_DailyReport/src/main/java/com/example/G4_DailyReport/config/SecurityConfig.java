package com.example.G4_DailyReport.config;

import com.example.G4_DailyReport.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    @SuppressWarnings("is deprecated and marked for removal")
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().anyRequest().permitAll().and().cors().and().csrf().disable();
//        return http.build();
//    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(jpaUserDetailsService);
        http.authorizeHttpRequests(
                auth -> {
                    auth
                            .anyRequest().authenticated();
                });
//        http.sessionManagement(
//                session ->
//                        session.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS)
//        );

        http.formLogin();
//        http.httpBasic(withDefaults());

//        http.csrf(csrf -> csrf.disable());
        //http.csrf(AbstractHttpConfigurer::disable);
//        http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
        // http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}