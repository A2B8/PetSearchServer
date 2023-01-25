package com.base.PetSearchServer.config;

import com.base.PetSearchServer.filter.JwtAuthentificationFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    private final JwtAuthentificationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + uploadPath);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();


//        http
//                .csrf()
//                .disable()
//                    .authorizeHttpRequests()
//                    //.requestMatchers("/auth/**", "/ads/all/**")
//                .requestMatchers("/**")
//                .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                    .authenticationProvider(authenticationProvider)
//                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
