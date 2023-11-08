package org.example.service;

import org.example.dtos.BrandDto;
import org.example.models.Brand;
import org.example.models.Offer;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<BrandDto> getAllBrand();

    BrandDto registerBrand(BrandDto brandDto);

    BrandDto updateBrand(String brandID, BrandDto updateBrand);

    void registerBrand(String townName);

    void deleteBrand(String brandID);


    List<BrandDto> findBrandByName(String name);
}
