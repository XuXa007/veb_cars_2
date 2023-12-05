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

//    public List<OfferDto> getAllOffers() {
//        return offerRepository.findAll().stream().map((s) -> modelMapper.map(s, OfferDto.class)).collect(Collectors.toList());
//    }

//    public OfferDto registerOffer(OfferDto offer) {
//
//        if(!this.validationUtil.isValid(offer)) {
//            this.validationUtil
//                    .violations(offer)
//                    .stream()
//                    .map(ConstraintViolation::getMessage)
//                    .forEach(System.out::println);
//            throw new IllegalArgumentException("Illegal arguments in Offer!");
//        }
//
//        Offer o = modelMapper.map(offer, Offer.class);
//        String offerId = o.getId();
//        if (offerId == null || offerRepository.findById(offerId).isEmpty()) {
//            return modelMapper.map(offerRepository.save(o), OfferDto.class);
//        } else {
//            throw new NotFoundException("A offer with this id already exists");
//        }
//    }

    public void deleteOffer(String offerID) {
        Offer offer = offerRepository.findById(offerID)
                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + offerID));
        offerRepository.delete(offer);
    }

//    public OfferDto updateOffer(String offerID, OfferDto updateOffer) {
//        Offer existingOffer = offerRepository.findById(offerID).orElseThrow(() -> new NotFoundException("Could not find" + offer + " by id: " + offerID));
//
//        Models existingModels = modelMapper.map(updateOffer.getModel(), Models.class);
//        Users existingUser = modelMapper.map(updateOffer.getUsers(), Users.class);
//
//        existingOffer.setModel(existingModels);
//        existingOffer.setUsers(existingUser);
//
////        existingOffer.setId(updateOffer.getId());
//        existingOffer.setDescription(updateOffer.getDescription());
//        existingOffer.setEngine(updateOffer.getEngine());
////        existingOffer.setImageUrl(updateOffer.getImageURL());
//        existingOffer.setMileage(updateOffer.getMileage());
//        existingOffer.setPrice(updateOffer.getPrice());
//        existingOffer.setTransmission(updateOffer.getTransmission());
//        existingOffer.setYear(updateOffer.getYear());
//        existingOffer.setCreated(updateOffer.getCreated());
//        existingOffer.setModified(updateOffer.getModified());
//
//        Offer savedOffer = offerRepository.save(existingOffer);
//        return modelMapper.map(savedOffer, OfferDto.class);
//    }


    public ShowOfferInfoDto offerDetails(String offerId) {
        return modelMapper.map(offerRepository.findById(offerId).orElse(null), ShowOfferInfoDto.class);
    }

    public void addOffer(AddOfferDto offerDto) {
        offerDto.setCreated(LocalDateTime.now());
        offerDto.setModified(LocalDateTime.now());

        Offer offer = modelMapper.map(offerDto, Offer.class);
//        offer.setModel(modelRepository.findModelsByName(offerDto.getModel()).orElse(null));
//        offer.setUsers(usersRepository.findUsersByUserName(offerDto.getUsers()).orElse(null));

        offerRepository.saveAndFlush(offer);
    }

    public List<ShowOfferInfoDto> getAll() {
        return offerRepository.findAll().stream().map((offer) -> modelMapper.map(offer, ShowOfferInfoDto.class)).collect(Collectors.toList());
    }
}
