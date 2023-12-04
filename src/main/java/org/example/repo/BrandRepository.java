package org.example.repo;


import org.example.models.Brand;
import org.example.models.Models;
import org.example.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    List <Brand> findAllByName (String name);

    Optional<Brand> findByName(String value);

    Optional<Brand> findBrandByName(String name);
    Brand getBrandById(String brandId);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
