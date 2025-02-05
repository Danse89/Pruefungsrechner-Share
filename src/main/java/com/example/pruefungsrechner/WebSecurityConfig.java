package com.example.pruefungsrechner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/customers").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers( "/startseite", "/css/**", "/js/**", "/images/**").permitAll() // Öffentliche Seiten
                        .requestMatchers("/Chatseite").authenticated() // Chat erfordert Login
                        .anyRequest().authenticated() // Alle anderen Seiten erfordern Login
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/startseite",true)
                        .permitAll() // Enable form-based login

                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendRedirect("/login") // Nicht authentifizierte Benutzer zur Login-Seite leiten
                        )

                )

                .httpBasic(); // Allow basic authenticationreturn
        return
        http.build();

    }
}