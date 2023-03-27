package com.example.myproject.repository;

import com.example.myproject.domain.entity.UserRoleEntity;
import com.example.myproject.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(Role role);
}
