package org.example.controllers;

import org.example.dtos.BrandDto;
import org.example.models.Brand;
import org.example.models.Offer;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    Iterable<BrandDto> all() {
        return brandService.getAllBrand();
    }


    @PostMapping("/")
    BrandDto newBrand(@RequestBody BrandDto newBrand) {
        return brandService.registerBrand(newBrand);
    }

    @DeleteMapping("/{brandID}")
    void deleteBrand(@PathVariable("brandID") BrandDto brandID) {
        brandService.registerBrand(brandID);
    }

    @PutMapping("/{brandID}")
    public BrandDto updateBrand(@PathVariable("brandID") String brandID, @RequestBody BrandDto updateBrand) {
        return brandService.updateBrand(brandID, updateBrand);
    }


}
