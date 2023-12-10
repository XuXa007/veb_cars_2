package org.example.repo;


import org.example.Enums.Category;
import org.example.Enums.Engine;
import org.example.models.Brand;
import org.example.models.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Models, String> {

    @Query("SELECT m FROM Models m JOIN m.brand b WHERE b.name = :brandName")
    List<Models> findAllByBrandName(@Param("brandName") String brandName);

    Optional<Models> findByName(String value);

    @Modifying
    @Transactional
    void deleteByName(String name);

    Optional<Models> findModelsByName(String model);

    List<Models> findByBrand(Brand brand);

//    Optional<Models> findByBrandNameAndCategoryAndEngine(String brandName, Category category, Engine engine);
//
//    Optional<Object> findByBrandNameAndCategory(String brandName, Category category);
//
//    Optional<Object> findByBrandNameAndEngine(String brandName, Engine engine);
//
//    Optional<Object> findByBrandName(String brandName);
//
//    Optional<Object> findByCategoryAndEngine(Category category, Engine engine);
//
//    Optional<Object> findByCategory(Category category);
//
//    Optional<Object> findByEngine(Engine engine);
}
