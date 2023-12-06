package org.example.service;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.*;
import org.example.exception.NotFoundException;
import org.example.models.Models;
import org.example.models.Offer;
import org.example.models.Users;
import org.example.repo.ModelRepository;
import org.example.repo.OfferRepository;
import org.example.repo.UsersRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UsersRepository usersRepository;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public OfferService(OfferRepository offerRepository, ModelRepository modelRepository, UsersRepository usersRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String offer = "offer";

    public void deleteOffer(String offerID) {
        Offer offer = offerRepository.findById(offerID)
                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + offerID));
        offerRepository.delete(offer);
    }


    public ShowOfferInfoDto offerDetails(String offerId) {
        return modelMapper.map(offerRepository.findById(offerId).orElse(null), ShowOfferInfoDto.class);
    }

    public void addOffer(AddOfferDto offer) {
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());
        Offer of = modelMapper.map(offer, Offer.class);
        of.setModel(modelRepository.findByName(offer.getModels()).orElse(null));
        of.setUsers(usersRepository.findByUserName(offer.getUsers()).orElse(null));
        offerRepository.saveAndFlush(of);
    }

    public List<ShowOfferInfoDto> getAll() {
        return offerRepository.findAll().stream().map((offer) -> modelMapper.map(offer, ShowOfferInfoDto.class)).collect(Collectors.toList());
    }

    public void removeOffer(String id) {
        offerRepository.deleteById(id);
    }
    public List<ShowOfferInfoDto> getAllSortedByMileage(String sortBy, String sortOrder) {
        List<Offer> offers = offerRepository.findAll();

        // Convert List<Offer> to List<ShowOfferInfoDto> using ModelMapper
        List<ShowOfferInfoDto> offerInfos = offers.stream()
                .map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());

        // Sort the list based on mileage
        offerInfos.sort((o1, o2) -> {
            int comparison;
            switch (sortBy.toLowerCase()) {
                case "mileage":
                    // Directly compare primitive int values
                    comparison = o1.getMileage() - o2.getMileage();
                    break;
                // Add more cases for other possible sorting fields
                default:
                    comparison = 0; // Default case, no sorting
            }

            // Adjust the order based on sortOrder
            return sortOrder.equalsIgnoreCase("desc") ? -comparison : comparison;
        });

        return offerInfos;
    }

}
