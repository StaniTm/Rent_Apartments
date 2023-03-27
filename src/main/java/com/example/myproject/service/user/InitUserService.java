package com.example.myproject.service.user;


import com.example.myproject.domain.entity.UserClientEntity;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.service.initDB.InitDB;
import com.example.myproject.service.userRole.UserRoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InitUserService implements InitDB {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitUserService(UserRepository userRepository, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if (isDbInit()) {
            this.createAdmin();
            this.createUser();
        }
    }


    public void createAdmin() {
        UserClientEntity adminUser = new UserClientEntity().
                setUsername("Admin").
                setPassword(passwordEncoder.encode("pass")).
                setCreatedOn(new Date()).
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("Admin@example.com").
                setRole(userRoleService.findAllRoles());

        this.userRepository.saveAndFlush(adminUser);
    }

    public void createUser() {
        UserClientEntity normalUser = new UserClientEntity().
                setUsername("User").
                setPassword(passwordEncoder.encode("pass")).
                setFirstName("User").
                setLastName("Userov").
                setEmail("User@example.com").
                setCreatedOn(new Date()).
                setRole(List.of(userRoleService.findRoleEntityByName("USER")));

        this.userRepository.saveAndFlush(normalUser);
    }

    public boolean isDbInit() {
        return this.userRepository.count() == 0;
    }
}
