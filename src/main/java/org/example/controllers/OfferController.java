package org.example.controllers;

import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.example.dtos.*;
import org.example.service.ModelService;
import org.example.service.OfferService;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private UsersService usersService;


    @PostMapping("/")
    OfferDto newOffer(@RequestBody OfferDto newOffer) {
        return offerService.registerOffer(newOffer);
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        List<ModelDto> modelList = modelService.getAllModels();
        List<UsersDto> userList = usersService.getAllUsers();
        model.addAttribute("modelList", modelList);
        model.addAttribute("userList", userList);
        return "offer-add";
    }

    @DeleteMapping("/{offerID}")
    void deleteOffer(@PathVariable("offerID") OfferDto offerDto) {
        offerService.registerOffer(offerDto);
    }

    @PutMapping("/{offerID}")
    public OfferDto updateOffer(@PathVariable("offerID") String offerID, @RequestBody OfferDto updateOffer) {
        return offerService.updateOffer(offerID, updateOffer);
    }
    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offer/add";
        }

        offerService.addOffer(offerModel);

        return "redirect:/offer/all";
    }

    @GetMapping("/all")
    public String showAllOffer(Model model) {
        model.addAttribute("allOffers", offerService.allOffers());

        return "offer-all";
    }

    @GetMapping("/offer-details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") String offerId, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(offerId));

        return "offer-details";
    }
}
