package org.example.repo;


import org.example.models.Brand;
import org.example.models.Model;
import org.example.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    @Query("SELECT m FROM Model m JOIN m.brand b WHERE b.name = :brandName")
    List<Model> findAllByBrandName(@Param("brandName") String brandName);

    Optional<Model> findByName(String value);

}
