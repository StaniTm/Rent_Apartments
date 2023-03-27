package com.example.myproject.repository;

import com.example.myproject.domain.entity.CityEntity;
import com.example.myproject.domain.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    Optional<List<OfferEntity>> findAllByApartmentRoomCount(Integer roomCount);

    Optional<List<OfferEntity>> findAllByApartment_City(CityEntity city);

    Optional<List<OfferEntity>> findAllByApartmentOwnerId(Long ownerId);
}
