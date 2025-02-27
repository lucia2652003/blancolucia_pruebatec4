package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //Le pedimos que los métodos GET no requieren de autenticación que empiece por /** y el resto si (POST, PUT y DELETE)
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorized ->
                        authorized.requestMatchers(HttpMethod.GET, "/**", "/doc").permitAll().anyRequest()
                                .authenticated()
                        )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                //Para establecer en la autorización el tipo de autor y le ponemos un nombre
                .httpBasic(httpBasic-> httpBasic.realmName("app"))
                .build();//Construye la seguridad
    }
}
