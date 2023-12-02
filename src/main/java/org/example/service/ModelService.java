package org.example.service;

import org.example.dtos.AddModelDto;
import org.example.dtos.ModelDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.models.Models;

import java.util.List;

public interface ModelService {
    List<ModelDto> getAllModels();

    ModelDto registerModel(ModelDto modelDto);

    void deleteModel(String modelID);

    ModelDto updateModel(String modelID, ModelDto updateModel);

    List<ModelDto> findModelByBrandName(String brandName);

    List<ShowModelInfoDto> allModels();

    ShowModelInfoDto modelDetails(String modelName);
    void addModel(AddModelDto modelDto);

    Models getModelById(String modelId);
}
