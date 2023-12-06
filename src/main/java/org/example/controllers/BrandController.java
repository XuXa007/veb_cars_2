package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.AddBrandDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.models.Brand;
import org.example.service.BrandService;
import org.example.service.ModelService;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;

    @Autowired
    private UsersService usersService;

    public BrandController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrand(@Valid AddBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brand/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/brand/all";
    }

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @GetMapping("/all")
    public String showAllBrands(Model model) {
        model.addAttribute("brandInfos", brandService.allBrands());

        return "brand-all";
    }


    @GetMapping("/brand-delete/{brand-name}")
    public String deleteBrand(@PathVariable("brand-name") String name) {
        brandService.removeBrand(name);

        return "redirect:/brand/all";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, Model model) {
        model.addAttribute("models", modelService.getModelsByBrand(brandName));
        model.addAttribute("brandDetails", brandService.brandDetails(brandName));

        return "brand-details";
    }

    @GetMapping("/edit/{brand-name}")
    public String editBrandForm(@PathVariable("brand-name") String brandName, Model model) {

        AddBrandDto brand = brandService.findBrandByName(brandName);

        model.addAttribute("brand", brand);
        return "brand-edit";
    }
    @PostMapping("/edit/{brand-name}")
    public String editBrand(@PathVariable("brand-name") String brandName, @Valid AddBrandDto brandDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("brand", brandDto);
            return "brand-edit";
        }

        brandService.editBrand(brandName, brandDto);
        return "redirect:/brand/all";
    }
}
