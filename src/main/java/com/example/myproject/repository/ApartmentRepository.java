package com.example.myproject.repository;

import com.example.myproject.domain.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {
    void deleteAllByOwnerId(Long ownerId);
    Optional<List<ApartmentEntity>> findAllByOwnerId(Long ownerId);
}
