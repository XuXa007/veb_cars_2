package org.example.repo;

import org.example.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    List<Offer> findAllByOrderByPriceAsc();

    List<Offer> findAllByOrderByPriceDesc();
}