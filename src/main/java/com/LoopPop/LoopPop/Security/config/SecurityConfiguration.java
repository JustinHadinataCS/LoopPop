package com.LoopPop.LoopPop.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> {
                    csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                            .ignoringRequestMatchers("/registration*", "/login*");
                })
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/registration*", "/login*", "/error", "/css/**", "/js/**", "/images/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic(withDefaults())
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/index", true)
                        .usernameParameter("email")
                        .permitAll())
                .logout(logout -> logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")));

        return http.build();
    }
}
