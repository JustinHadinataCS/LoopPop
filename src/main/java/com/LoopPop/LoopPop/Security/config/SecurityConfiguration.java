package com.LoopPop.LoopPop.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

// Mark this class as a configuration class for Spring security
@Configuration
// Enable Spring Security's web security support
@EnableWebSecurity
public class SecurityConfiguration {

    // Define a bean for the security filter chain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure CSRF protection
                .csrf(csrf -> csrf
                        // Use a cookie-based CSRF token repository and make the token available to JavaScript
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // Disable CSRF protection for specific endpoints
                        .ignoringRequestMatchers("/registration*", "/login*", "/api/v1/comments")
                )
                // Configure authorization rules
                .authorizeRequests(authorize -> authorize
                        // Allow unauthenticated access to specific paths
                        .requestMatchers("/registration*", "/login*", "/error", "/css/**", "/js/**", "/images/**", "/", "/api/v1/comments").permitAll()
                        // Require authentication for any other request
                        .anyRequest().authenticated()
                )
                // Configure form login
                .formLogin(form -> form
                        // Set the login page URL
                        .loginPage("/login")
                        // Set the default success URL after login
                        .defaultSuccessUrl("/main", true)
                        // Specify the parameter name for the username (email in this case)
                        .usernameParameter("email")
                        // Allow everyone to access the login page
                        .permitAll()
                )
                // Configure logout functionality
                .logout(logout -> logout
                        // Invalidate the HTTP session on logout
                        .invalidateHttpSession(true)
                        // Clear the authentication information on logout
                        .clearAuthentication(true)
                        // Set the logout request URL
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );

        // Build and return the HttpSecurity object
        return http.build();
    }
}
