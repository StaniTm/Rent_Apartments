package com.example.myproject.config.security;


import com.example.myproject.domain.enums.Role;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.service.user.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                // defines which pages will be authorized
                authorizeHttpRequests()
                // allow access to all static files (images, CSS, js)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // the URL-s below are available for all users - logged in and anonymous
                .requestMatchers("/", "/home", "/offers/Sunny Beach", "/offers/Sofia", "/offers/Plovdiv", "/offers/selected/**","/offers/all/room/size/**", "/offers/*/room/size/*", "/offers/all", "/user/login", "/user/register", "/user/login-error").permitAll()
                //only for logged users
                .requestMatchers("/booking/**", "/rent/**", "/offers/user/apartments").authenticated()
                // only for admins
                .requestMatchers("/admin").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                // configure login with HTML form
                .and()
                .formLogin()
                .loginPage("/user/login")
                // the names of the username, password input fields in the custom login form
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // where do we go after login
                .failureForwardUrl("/user/login-error")//use true argument if you always want to go there, otherwise go to previous page
                //configure logout
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                //go to homepage after logout
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }
}
