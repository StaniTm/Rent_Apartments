package com.example.myproject.repository;

import com.example.myproject.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    Optional<List<BookingEntity>> findAllByOffer_Id(Long offerId);
}
