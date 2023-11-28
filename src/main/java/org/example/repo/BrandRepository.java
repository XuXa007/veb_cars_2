package org.example.repo;


import org.example.models.Brand;
import org.example.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface BrandRepository extends JpaRepository<Brand, String> {
    List <Brand> findAllByName (String name);

    Optional<Brand> findByName(String value);
}
