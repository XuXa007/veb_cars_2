package org.example.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.example.dtos.*;
import org.example.service.BrandService;
import org.example.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/model")
public class ModelController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    private ModelService modelService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/add")
    public String addModel(Model model, Principal principal) {
        model.addAttribute("brandList", brandService.getAll());
        return "model-add";
    }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }


    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, Model model) {

        ShowModelInfoDto modelDetails = modelService.modelDetails(modelName);

        String brand = modelDetails.getBrandName();
        String modelDetailsName = modelDetails.getName();

        String imageUrl = "https://ya.ru/images/search?img_url=https%3A%2F%2Fwww.turkey-visit.com%2Fimages%2Funited-states%2F"+brand+modelDetailsName +".jpg&lr=10393&nl=1&pos=3&rpt=simage&source=morda&text=i-8&utm_source=main_stripe_big";

        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("modelDetails", modelDetails);

        return "model-details";
    }

    @GetMapping("/all")
    public String showAllModels(Model model, Principal principal) {
        if (principal != null) {
            LOG.log(Level.INFO, "Show all models for " + principal.getName());
        }

        model.addAttribute("modelInfos", modelService.getAllModels());
        model.addAttribute("brands", brandService.getAll());

        return "model-all";
    }

    private void populateModel(Model model) {

    }

    @PostMapping("/add")
    public String addModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        LOG.log(Level.INFO, "Add model by " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/model/add";
        }
        modelService.addModel(modelModel);
        return "redirect:/brand/all";
    }

    @GetMapping("/model-delete/{model-name}")
    public String deleteModel(@PathVariable("model-name") String name) {
        modelService.removeModel(name);

        return "redirect:/brand/all";
    }

    @GetMapping("/edit/{model-name}")
    public String editModelForm(@PathVariable("model-name") String modelName, Model model) {

        AddModelDto modelDto = modelService.findModelByName(modelName);
        model.addAttribute("availableBrands", brandService.getAll());

        model.addAttribute("model", modelDto);
        return "model-edit";
    }

    @PostMapping("/edit/{model-name}")
    public String editModel(@PathVariable("model-name") String modelName, @Valid AddModelDto modelDto, BindingResult result, Model model) {

        model.addAttribute("availableBrands", brandService.getAll());
        if (result.hasErrors()) {
            model.addAttribute("model", modelDto);
            return "model-edit";
        }

        modelService.editModel(modelName, modelDto);
        return "redirect:/all";
    }



}
