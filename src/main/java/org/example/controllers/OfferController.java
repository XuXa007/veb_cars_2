package org.example.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/offer")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    private OfferService offerService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private UsersService usersService;


    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap, Principal principal) {
        LOG.log(Level.INFO, "Add offer by " + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offer/add";
        }

        offerService.addOffer(offerModel, principal.getName());

        return "redirect:/offer/all";
    }


    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("listModels", modelService.getAllModelsForOffer());
        model.addAttribute("userList", usersService.getAllUsersForOffer());
        return "offer-add";
    }

    @GetMapping("/offer-details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") String offerId, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(offerId));

        return "offer-details";
    }

    @GetMapping("/all")
    public String showAllOffers(Model model, Principal principal) {
        if (principal != null) {
            LOG.log(Level.INFO, "Show all offers by " + principal.getName());
        }
        model.addAttribute("addOffer", offerService.getAll());
        model.addAttribute("modelInfos", modelService.getAllModels());

        return "offer-all";
    }

    @GetMapping("/delete/{offer-id}")
    public String deleteOffer(@PathVariable("offer-id") String id) {
        offerService.removeOffer(id);

        return "redirect:/offer/all";
    }
}