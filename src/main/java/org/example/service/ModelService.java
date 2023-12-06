package org.example.service;

import org.example.dtos.AddModelDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.models.Brand;
import org.example.models.Models;
import org.example.repo.BrandRepository;
import org.example.repo.ModelRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;


    @Autowired
    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String model = "model";

    public List<ShowModelInfoDto> getAllModels() {
        return modelRepository.findAll().stream().map((models) -> modelMapper.map(models, ShowModelInfoDto.class)).collect(Collectors.toList());
    }



    public void addModel(AddModelDto addModelDto) {
        addModelDto.setCreated(LocalDateTime.now());
        addModelDto.setModified(LocalDateTime.now());
        addModelDto.setImageURL("ooopss...");
        Models model = modelMapper.map(addModelDto, Models.class);
        model.setBrand(brandRepository.findByName(addModelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);
    }

    public List<ShowModelInfoDto> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName).orElse(null), ShowModelInfoDto.class);
    }

    public Models getModelById(String modelId) {
        return modelRepository.findById(modelId).orElse(null);
    }

    public void removeModel(String name) {
        modelRepository.deleteByName(name);
    }

    public List<ShowModelInfoDto> getAllModelsForOffer() {
        return modelRepository.findAll().stream().map((model) -> modelMapper.map(model, ShowModelInfoDto.class)).collect(Collectors.toList());
    }

    public List<ShowModelInfoDto> getModelsByBrand(String brandName) {
        Brand brand = brandRepository.findByName(brandName).orElseThrow(() -> new ExpressionException("Brand not found with name: " + brandName));

        List<Models> models = modelRepository.findByBrand(brand);
        return models.stream()
                .map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }
}
