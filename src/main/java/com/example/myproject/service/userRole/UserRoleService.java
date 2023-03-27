package com.example.myproject.service.userRole;


import com.example.myproject.domain.entity.UserRoleEntity;
import com.example.myproject.domain.enums.Role;
import com.example.myproject.domain.model.dtoS.user.UserRoleModel;
import com.example.myproject.domain.model.view.UserRoleViewModel;
import com.example.myproject.repository.UserRoleRepository;
import com.example.myproject.service.initDB.InitDB;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserRoleService implements InitDB {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if (!isDbInit()) {
            List<UserRoleEntity> roles = new ArrayList<>();

            roles.add(new UserRoleEntity().setRole(Role.USER));
            roles.add(new UserRoleEntity().setRole(Role.ADMIN));

            this.userRoleRepository.saveAllAndFlush(roles);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.userRoleRepository.count() > 0;
    }

    public List<UserRoleViewModel> getAll() {
        return this.userRoleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserRoleEntity> findAllRoles() {
        return this.userRoleRepository.findAll();
    }

    public UserRoleModel findRoleByName(String name) {
        return this.modelMapper.map(this.userRoleRepository.findByRole(Role.valueOf(name))
                        .orElseThrow(NoSuchElementException::new),
                UserRoleModel.class);
    }

    public UserRoleEntity findRoleEntityByName(String name) {
        return this.userRoleRepository.findByRole(Role.valueOf(name))
                .orElseThrow(NoSuchElementException::new);
    }
}
