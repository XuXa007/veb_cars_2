package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.*;
import org.example.models.Brand;
import org.example.models.Models;
import org.example.service.BrandService;
import org.example.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/add")
    public String addModel(Model model) {
        List<BrandDto> brandList = brandService.getAllBrand();
        model.addAttribute("brandList", brandList);
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


    public String getModelsByBrandName(@RequestParam String brandName, ModelMap model) {
        List<ModelDto> models = modelService.findModelByBrandName(brandName);
        model.addAttribute("models", models);
        return "modelList";
    }

    @PostMapping("/add")
    public String addModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/model/add";
        }
//        String brandId = modelModel.getBrandId();
//        Brand brand = brandService.getBrandById(brandId);
//
//        modelModel.setBrandId(brandId);
        modelService.addModel(modelModel);

        return "redirect:/model/all";
    }

    @GetMapping("/model-delete/{model-name}")
    public String deleteModel(@PathVariable("model-name") String name) {
        modelService.removeModel(name);

        return "redirect:/model/all";
    }
}
