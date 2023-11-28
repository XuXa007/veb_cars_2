package org.example.controllers;

import org.example.dtos.AddModelDto;
import org.example.dtos.AddOfferDto;
import org.example.dtos.ModelDto;
import org.example.dtos.OfferDto;
import org.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @PostMapping("/")
    OfferDto newOffer(@RequestBody OfferDto newOffer) {
        return offerService.registerOffer(newOffer);
    }

    @DeleteMapping("/{offerID}")
    void deleteOffer(@PathVariable("offerID") OfferDto offerDto) {
        offerService.registerOffer(offerDto);
    }

    @PutMapping("/{offerID}")
    public OfferDto updateOffer(@PathVariable("offerID") String offerID, @RequestBody OfferDto updateOffer) {
        return offerService.updateOffer(offerID, updateOffer);
    }

    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }

    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }

    @GetMapping("/all")
    public String showAllOffer(Model model) {
        model.addAttribute("offerInfos", offerService.allOffer());

        return "model-all";
    }
}
