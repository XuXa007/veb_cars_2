package org.example.service;

import org.example.dtos.AddModelDto;
import org.example.dtos.ModelDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.models.Models;
import org.example.repo.BrandRepository;
import org.example.repo.ModelRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ShowModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName).orElse(null), ShowModelInfoDto.class);
    }

    public Models getModelById(String modelId) {
        return modelRepository.findById(modelId).orElse(null);
    }

    public void removeModel(String name) {
        modelRepository.deleteByName(name);
    }
}
