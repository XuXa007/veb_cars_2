package org.example.service;

import org.example.dtos.AddBrandDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.models.Brand;
import org.example.repo.BrandRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    @Cacheable("brand")
    public List<ShowBrandInfoDto> allBrands() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, ShowBrandInfoDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("brand")
    public ShowBrandInfoDto brandDetails(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName).orElse(null), ShowBrandInfoDto.class);
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    public void addBrand(AddBrandDto brandDto) {
        brandDto.setCreated(LocalDateTime.now());
        brandDto.setModified(LocalDateTime.now());
        Brand brand = modelMapper.map(brandDto, Brand.class);
        brandRepository.saveAndFlush(brand);
    }

    public Brand getBrandById(String brandId) {
        return brandRepository.findById(brandId).orElse(null);

    }
    @CacheEvict(cacheNames = "brand", allEntries = true)
    public void removeBrand(String name) {
        brandRepository.deleteByName(name);
    }

    @Cacheable("brand")
    public List<ShowBrandInfoDto> getAll() {
        return brandRepository.findAll().stream().map((brand) -> modelMapper.map(brand, ShowBrandInfoDto.class)).collect(Collectors.toList());
    }


    @Transactional
    public void updateBrand(String brandName, String updatedName) {
        Brand brand = brandRepository.findByName(brandName)
                .orElseThrow(() -> new ExpressionException("Brand not found with name: " + brandName));

        // Обновление информации о бренде
        brand.setName(updatedName);
        brandRepository.save(brand);
    }

    @Cacheable("brand")
    public AddBrandDto findBrandByName(String brandName) {
        return brandRepository.findByName(brandName)
                .map(brand -> modelMapper.map(brand, AddBrandDto.class))
                .orElse(null);
    }

    @CacheEvict(cacheNames = "brand", allEntries = true)
    public void editBrand(String originalBrandName, AddBrandDto brandDto) {
        Optional<Brand> existingBrandOptional = brandRepository.findByName(originalBrandName);

        if (existingBrandOptional.isPresent()) {
            Brand existingBrand = existingBrandOptional.get();
            existingBrand.setName(brandDto.getName());
            existingBrand.setModified(LocalDateTime.now());

            brandRepository.saveAndFlush(existingBrand);
        } else {
            throw new NoSuchElementException("Brand not found for update: " + originalBrandName);
        }
    }

    public ShowBrandInfoDto getBrandByName(String brandName) {
        Optional<Brand> optionalBrand = brandRepository.findByName(brandName);

        return optionalBrand.map(this::mapBrandToDto).orElse(null);
    }

    private ShowBrandInfoDto mapBrandToDto(Brand brand) {
        ShowBrandInfoDto brandInfoDto = new ShowBrandInfoDto();
        brandInfoDto.setName(brand.getName());

        return brandInfoDto;
    }
}


