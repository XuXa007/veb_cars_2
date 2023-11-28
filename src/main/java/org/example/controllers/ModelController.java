package org.example.controllers;

import org.example.dtos.AddBrandDto;
import org.example.dtos.AddModelDto;
import org.example.dtos.BrandDto;
import org.example.dtos.ModelDto;
import org.example.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/add")
    public String addModel() {
        return "model-add";
    }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @GetMapping("/all")
    public String showAllModels(Model model) {
        model.addAttribute("modelInfos", modelService.allModels());

        return "model-all";
    }
    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, Model model) {
        model.addAttribute("modelDetails", modelService.modelDetails(modelName));

        return "model-details";
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
