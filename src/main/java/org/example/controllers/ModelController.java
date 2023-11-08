package org.example.controllers;

import org.example.dtos.BrandDto;
import org.example.dtos.ModelDto;
import org.example.models.Model;
import org.example.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/")
    Iterable<ModelDto> all() {
        return modelService.getAllModels();
    }

    @PostMapping("/")
    ModelDto newModel(@RequestBody ModelDto newModel) {
        return modelService.registerModel(newModel);
    }

    @DeleteMapping("/{modelID}")
    void deleteModel(@PathVariable("modelID") ModelDto modelID) {
        modelService.registerModel(modelID);
    }

    @PutMapping("/{modelID}")
    public ModelDto updateModel(@PathVariable("modelID") String modelID, @RequestBody ModelDto updateModel) {
        return modelService.updateModel(modelID, updateModel);
    }
    public String getModelsByBrandName(@RequestParam String brandName, ModelMap model) {
        List<ModelDto> models = modelService.findModelByBrandName(brandName);
        model.addAttribute("models", models);
        return "modelList";
    }


}
