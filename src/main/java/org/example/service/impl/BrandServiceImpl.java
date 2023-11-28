package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.BrandDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.ex.BrandConflictException;
import org.example.exception.NotFoundException;
import org.example.models.Brand;
import org.example.models.Model;
import org.example.repo.BrandRepository;
import org.example.repo.ModelRepository;
import org.example.service.BrandService;
import org.example.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository, ValidationUtil validationUtil) {
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

    @Override
    public List<BrandDto> getAllBrand() {
        return brandRepository.findAll().stream().map((s) -> modelMapper.map(s, BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public BrandDto registerBrand(BrandDto brand) {
        if (!this.validationUtil.isValid(brand)) {
            this.validationUtil
                    .violations(brand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments in Brand!");
        }
        Brand b = modelMapper.map(brand, Brand.class);
        String brandId = b.getId();
        if (brandId == null || brandRepository.findById(brandId).isEmpty()) {
            return modelMapper.map(brandRepository.save(b), BrandDto.class);
        } else {
            throw new NotFoundException("A brand with this id already exists");
        }
    }


    @Override
    public void deleteBrand(String brandID) {
        Brand brand = brandRepository.findById(brandID)
                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + brandID));

        brandRepository.delete(brand);
    }


    @Override
    public BrandDto updateBrand(String brandID, BrandDto updateBrand) {
        Brand existingBrand = brandRepository.findById(brandID).orElseThrow(() -> new NotFoundException("Could not find" + brand + " by id: " + brandID));

//        existingBrand.setId(updateBrand.getId());
        existingBrand.setName(updateBrand.getName());
        existingBrand.setCreated(updateBrand.getCreated());
        existingBrand.setModified(updateBrand.getModified());

        Brand savedBrand = brandRepository.save(existingBrand);
        return modelMapper.map(savedBrand, BrandDto.class);
    }

//    @Autowired
//    public void setBrandRepository(BrandRepository brandRepository) {
//        this.brandRepository = brandRepository;
//    }

    @Override
    public List<BrandDto> findBrandByName (String name) {
        return brandRepository.findAllByName(name).stream().map((s) -> modelMapper.map(s, BrandDto.class)).collect(Collectors.toList());
    }
}
