package org.example.service;

import org.example.dtos.AddBrandDto;
import org.example.dtos.BrandDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.models.Brand;
import org.example.repo.BrandRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService  {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandService(ModelMapper modelMapper, BrandRepository brandRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.validationUtil = validationUtil;
    }

    private String brand = "brand";

    public List<ShowBrandInfoDto> allBrands() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, ShowBrandInfoDto.class))
                .collect(Collectors.toList());
    }


    public ShowBrandInfoDto brandDetails(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName).orElse(null), ShowBrandInfoDto.class);
    }

    public void addBrand(AddBrandDto brandDto) {
        brandDto.setCreated(LocalDateTime.now());
        brandDto.setModified(LocalDateTime.now());
        Brand brand = modelMapper.map(brandDto, Brand.class);
        brandRepository.saveAndFlush(brand);
    }

    public Brand getBrandById(String brandId) {
        return brandRepository.findById(brandId).orElse(null);

    }

    public void removeBrand(String name) {
        brandRepository.deleteByName(name);
    }

    public List<BrandDto> getAllBrand() {
        return brandRepository.findAll().stream().map((s) -> modelMapper.map(s, BrandDto.class)).collect(Collectors.toList());
    }


    public List<BrandDto> findBrandByName (String name) {
        return brandRepository.findAllByName(name).stream().map((s) -> modelMapper.map(s, BrandDto.class)).collect(Collectors.toList());
    }
}
