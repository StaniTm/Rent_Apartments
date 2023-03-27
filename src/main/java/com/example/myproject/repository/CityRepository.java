package com.example.myproject.repository;

import com.example.myproject.domain.entity.CityEntity;
import com.example.myproject.domain.enums.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> findByName(City name);
}
