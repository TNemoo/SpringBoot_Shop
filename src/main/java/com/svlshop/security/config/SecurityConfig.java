package com.svlshop.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(a -> a
                                .requestMatchers("/**").permitAll()
//                        .requestMatchers("/", "/index", "/registration", "/errorPage", "/favicon.ico").permitAll()
                                .requestMatchers("/admins/**").hasAuthority("ADMIN")
                                .requestMatchers("/products/**").hasAnyAuthority("MANAGER", "ADMIN")
                                .requestMatchers("/users/**").hasAnyAuthority("USER", "MANAGER", "ADMIN")
                                .anyRequest().authenticated()
                ).formLogin(f -> f
                        .loginPage("/login")
                        .usernameParameter("phoneNumber")
                        .defaultSuccessUrl("/index", true)
                        .loginProcessingUrl("/process_login")
                        .failureForwardUrl("/loginError")
                        .permitAll()
                ).logout(l -> l
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index")
                        .permitAll()
                ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1");
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
}