package com.master_diploma.cafe.config;

import com.master_diploma.cafe.services.MyUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;
    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/registered", "/login", "api/v1/apps/welcome", "api/v1/apps/new-user",
                                "/css/**", "/js/**").permitAll()
                        .requestMatchers("api/v1/apps/new-order", "api/v1/apps/all-orders").hasAnyRole("WAITER", "CLIENT")
                        .requestMatchers("api/v1/apps/update-status").hasRole("WAITER")
                        .requestMatchers("api/v1/apps/new-reserve").hasAnyRole("CLIENT", "WAITER")
                        .requestMatchers("api/v1/apps/new-desk").hasRole("WAITER")
                        .requestMatchers("api/v1/apps/user-orders/**", "api/v1/apps/desks/**", "api/v1/apps/desk/**").hasAnyRole("WAITER", "CLIENT")
                        .requestMatchers("api/v1/apps/ingredients/**").hasAnyRole("COOK")
                        .requestMatchers("api/v1/apps/dishes/view", "api/v1/apps/user-orders/**", "api/v1/apps/order-details/**", "api/v1/apps/institutions"
                                , "api/v1/apps/reviews/add", "api/v1/apps/add/**", "api/v1/apps/desksByInstitution/**", "api/v1/apps/menu/**").hasAnyRole("CLIENT", "WAITER", "COOK", "OWNER")
                        .requestMatchers("api/v1/apps/dishes/save").hasRole("COOK")
                        .requestMatchers("api/v1/apps/dishes/addFavorite/**").hasRole("CLIENT")
                        .requestMatchers("api/v1/apps/storages/view").hasAnyRole("OWNER", "COOK")
                        .requestMatchers("api/v1/apps/storages/save", "api/v1/apps/all-workers", "api/v1/apps/update/role").hasAnyRole("OWNER")
                        .requestMatchers("api/v1/apps/getId").authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll()
                        .successHandler(customSuccessHandler())
                )
                .build();
    }
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                setDefaultTargetUrl("/api/v1/apps/institutions");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
