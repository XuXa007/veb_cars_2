package org.example.service;

import org.example.dtos.AddBrandDto;
import org.example.dtos.BrandDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.models.Brand;
import org.example.models.Offer;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<BrandDto> getAllBrand();

    BrandDto registerBrand(BrandDto brandDto);

    BrandDto updateBrand(String brandID, BrandDto updateBrand);

    void deleteBrand(String brandID);

    List<ShowBrandInfoDto> allBrands();

    List<BrandDto> findBrandByName(String name);

    ShowBrandInfoDto brandDetails(String brandName);
    void addBrand(AddBrandDto brandDto);
}
