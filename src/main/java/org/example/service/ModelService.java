package org.example.service;

import org.example.dtos.AddModelDto;
import org.example.dtos.ModelDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.dtos.ShowModelInfoDto;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    List<ModelDto> getAllModels();

    ModelDto registerModel(ModelDto modelDto);

    void deleteModel(String modelID);

    ModelDto updateModel(String modelID, ModelDto updateModel);

    List<ModelDto> findModelByBrandName(String brandName);

    List<ShowModelInfoDto> allModels();

    ShowModelInfoDto modelDetails(String modelName);
    void addModel(AddModelDto modelDto);
}
