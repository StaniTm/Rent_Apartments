package com.example.myproject.repository;

import com.example.myproject.domain.entity.UserClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserClientEntity, Long> {

    Optional<UserClientEntity> findByUsername(String username);
}
