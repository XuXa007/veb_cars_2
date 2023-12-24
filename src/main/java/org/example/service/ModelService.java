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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    @Cacheable("model")
    public List<ShowModelInfoDto> getAllModels() {
        return modelRepository.findAll().stream().map((models) -> modelMapper.map(models, ShowModelInfoDto.class)).collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "model", allEntries = true)
    public void addModel(AddModelDto addModelDto) {
        addModelDto.setCreated(LocalDateTime.now());
        addModelDto.setModified(LocalDateTime.now());
        Models model = modelMapper.map(addModelDto, Models.class);
        model.setBrand(brandRepository.findByName(addModelDto.getBrand()).orElse(null));
        modelRepository.saveAndFlush(model);
    }

    @Cacheable("model")
    public List<ShowModelInfoDto> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }


    @Cacheable("model")
    public ShowModelInfoDto modelDetails(String modelName) {
        return modelMapper.map(modelRepository.findByName(modelName).orElse(null), ShowModelInfoDto.class);
    }


    @CacheEvict(cacheNames = "model", allEntries = true)
    public void removeModel(String name) {
        modelRepository.deleteByName(name);
    }

    @Cacheable("model")
    public List<ShowModelInfoDto> getAllModelsForOffer() {
        return modelRepository.findAll().stream().map((model) -> modelMapper.map(model, ShowModelInfoDto.class)).collect(Collectors.toList());
    }

    @Cacheable("model")
    public List<ShowModelInfoDto> getModelsByBrand(String brandName) {
        Brand brand = brandRepository.findByName(brandName).orElseThrow(() -> new ExpressionException("Brand not found with name: " + brandName));

        List<Models> models = modelRepository.findByBrand(brand);
        return models.stream()
                .map(model -> modelMapper.map(model, ShowModelInfoDto.class))
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "model", allEntries = true)
    public void editModel(String originalModelName, AddModelDto modelDto) {
        Optional<Models> existingModelOptional = modelRepository.findByName(originalModelName);

        if (existingModelOptional.isPresent()) {
            Models existingModel = existingModelOptional.get();

            Optional<Brand> brandOptional = brandRepository.findByName(modelDto.getBrand());
            if (brandOptional.isPresent()) {
                existingModel.setBrand(brandOptional.get());
            } else {
                throw new NoSuchElementException("Brand not found: " + modelDto.getBrand());
            }

            existingModel.setName(modelDto.getName());
            existingModel.setCategory(modelDto.getCategory());
            existingModel.setImageUrl(modelDto.getImageURL());
            existingModel.setStartYear(modelDto.getStartYear());
            existingModel.setEndYear(modelDto.getEndYear());
            existingModel.setModified(LocalDateTime.now());

            modelRepository.saveAndFlush(existingModel);
        } else {
            throw new NoSuchElementException("Model not found for update: " + originalModelName);
        }
    }

    @Cacheable("model")
    public AddModelDto findModelByName(String modelName) {
        return modelRepository.findByName(modelName)
                .map(model -> modelMapper.map(model, AddModelDto.class))
                .orElse(null);
    }
}
