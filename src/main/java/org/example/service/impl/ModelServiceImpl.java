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
//    @Override
//    public ModelDto registerModel(ModelDto model) {
//
//        if (!this.validationUtil.isValid(model)) {
//            this.validationUtil
//                    .violations(model)
//                    .stream()
//                    .map(ConstraintViolation::getMessage)
//                    .forEach(System.out::println);
//            throw new IllformedLocaleException("Illegal arguments in  Model!");
//        }
//
//        Models m = modelMapper.map(model, Models.class);
//        String modelId = m.getId();
//        if (modelId == null || modelRepository.findById(modelId).isEmpty()) {
//            return modelMapper.map(modelRepository.save(m), ModelDto.class);
//        } else {
//            throw new NotFoundException("A brand with this id already exists");
//        }
//    }

    public void addModel(AddModelDto modelDto) {
        modelDto.setCreated(LocalDateTime.now());
        modelDto.setModified(LocalDateTime.now());
        modelDto.setImageURL("ooopss...");
        Models model = modelMapper.map(modelDto, Models.class);
        model.setBrand(brandRepository.findBrandByName(modelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);

    }

//    @Override
//    public void deleteModel(String modelID) {
//        Models models = modelRepository.findById(modelID)
//                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + modelID));
//        modelRepository.delete(models);
//    }

//    @Override
//    public ModelDto updateModel(String modelID, ModelDto updateModel) {
//        Models existingModels = modelRepository.findById(modelID).orElseThrow(() -> new NotFoundException("Could not find" + model + " by id: " + modelID));
//
//        Brand existingBrand = modelMapper.map(updateModel.getBrand(), Brand.class);
//
//        existingModels.setBrand(existingBrand);
//
//        existingModels.setName(updateModel.getName());
//        existingModels.setImageUrl(updateModel.getImageURL());
//        existingModels.setStartYear(updateModel.getStartYear());
//        existingModels.setEndYear(updateModel.getEndYear());
//        existingModels.setCreated(updateModel.getCreated());
//        existingModels.setModified(updateModel.getModified());
//
//        Models savedModels = modelRepository.save(existingModels);
//        return modelMapper.map(savedModels, ModelDto.class);
//    }

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
