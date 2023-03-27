package com.example.myproject.service.user;


import com.example.myproject.domain.entity.UserClientEntity;
import com.example.myproject.domain.entity.UserRoleEntity;
import com.example.myproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.
                findByUsername(username).
                map(this::mapUserDetails).
                orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private UserDetails mapUserDetails(UserClientEntity userClientEntity) {
        return new User(
                userClientEntity.getUsername(),
                userClientEntity.getPassword(),
                getAuthorities(userClientEntity)
        );
    }

    private List<GrantedAuthority> getAuthorities(UserClientEntity userClientEntity) {
        return userClientEntity.
                getRole().
                stream().
                map(this::mapRole).
                toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());
    }
}
