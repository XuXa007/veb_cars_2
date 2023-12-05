package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.AddModelDto;
import org.example.dtos.ModelDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.exception.NotFoundException;
import org.example.models.Brand;
import org.example.models.Models;
import org.example.repo.BrandRepository;
import org.example.repo.ModelRepository;
import org.example.service.ModelService;
import org.example.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;


    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String model = "model";

    @Override
    public List<ModelDto> getAllModels() {
        return modelRepository.findAll().stream().map((s) -> modelMapper.map(s, ModelDto.class)).collect(Collectors.toList());
    }


    public void addModel(AddModelDto modelDto) {
        modelDto.setCreated(LocalDateTime.now());
        modelDto.setModified(LocalDateTime.now());
        modelDto.setImageURL("ooopss...");

        Models model = modelMapper.map(modelDto, Models.class);
        model.setBrand(brandRepository.findBrandByName(modelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);
    }


    @Override
    public List<ModelDto> findModelByBrandName(String brandName) {
        return modelRepository.findAllByBrandName(brandName)
                .stream()
                .map((s) -> modelMapper.map(s, ModelDto.class))
                .collect(Collectors.toList());
    }



    public List<ShowModelInfoDto> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName).orElse(null), ShowModelInfoDto.class);
    }

    @Override
    public Models getModelById(String modelId) {
        return modelRepository.findById(modelId).orElse(null);
    }

    @Override
    public void removeModel(String name) {
        modelRepository.deleteByName(name);
    }
}
