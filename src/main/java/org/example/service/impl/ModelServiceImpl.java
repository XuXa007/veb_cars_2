package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.AddModelDto;
import org.example.dtos.ModelDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.exception.NotFoundException;
import org.example.models.Brand;
import org.example.models.Model;
import org.example.repo.ModelRepository;
import org.example.repo.OfferRepository;
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

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;


    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String model = "model";

    @Override
    public List<ModelDto> getAllModels() {
        return modelRepository.findAll().stream().map((s) -> modelMapper.map(s, ModelDto.class)).collect(Collectors.toList());
    }
    @Override
    public ModelDto registerModel(ModelDto model) {

        if (!this.validationUtil.isValid(model)) {
            this.validationUtil
                    .violations(model)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllformedLocaleException("Illegal arguments in  Model!");
        }

        Model m = modelMapper.map(model, Model.class);
        String modelId = m.getId();
        if (modelId == null || modelRepository.findById(modelId).isEmpty()) {
            return modelMapper.map(modelRepository.save(m), ModelDto.class);
        } else {
            throw new NotFoundException("A brand with this id already exists");
        }
    }

    public void addModel(AddModelDto modelDto) {
        modelDto.setCreated(LocalDateTime.now());
        modelDto.setModified(LocalDateTime.now());
        modelDto.setImageURL("ooopss...");
        Model model = modelMapper.map(modelDto, Model.class);
        modelRepository.saveAndFlush(model);
    }

    @Override
    public void deleteModel(String modelID) {
        Model model = modelRepository.findById(modelID)
                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + modelID));
        modelRepository.delete(model);
    }

    @Override
    public ModelDto updateModel(String modelID, ModelDto updateModel) {
        Model existingModel = modelRepository.findById(modelID).orElseThrow(() -> new NotFoundException("Could not find" + model + " by id: " + modelID));

        Brand existingBrand = modelMapper.map(updateModel.getBrand(), Brand.class);

        existingModel.setBrand(existingBrand);

//        existingModel.setId(updateModel.getId());
        existingModel.setName(updateModel.getName());
        existingModel.setImageUrl(updateModel.getImageURL());
        existingModel.setStartYear(updateModel.getStartYear());
        existingModel.setEndYear(updateModel.getEndYear());
        existingModel.setCreated(updateModel.getCreated());
        existingModel.setModified(updateModel.getModified());

        Model savedModel = modelRepository.save(existingModel);
        return modelMapper.map(savedModel, ModelDto.class);
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

}
