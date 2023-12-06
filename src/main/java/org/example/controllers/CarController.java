package org.example.controllers;

import org.example.models.Brand;
import org.example.service.BrandService;
import org.example.service.ModelService;
import org.example.service.OfferService;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class CarController {
    @Autowired
    private ModelService modelService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private OfferService offerService;

    public CarController(ModelService modelService, BrandService brandService, UsersService usersService, OfferService offerService) {
        this.modelService = modelService;
        this.brandService = brandService;
        this.usersService = usersService;
        this.offerService = offerService;
    }

    @GetMapping("/car/all")
    public String allCar(Model model) {
        model.addAttribute("brandInfos", brandService.allBrands());
        model.addAttribute("modelInfos", modelService.allModels());
        model.addAttribute("offerInfos", offerService.getAll());
        model.addAttribute("usersInfos", usersService.getAll());
        return "car-all";
    }

}
