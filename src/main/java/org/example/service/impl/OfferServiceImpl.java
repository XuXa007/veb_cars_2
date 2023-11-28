package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.BrandDto;
import org.example.dtos.OfferDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.dtos.ShowOfferInfoDto;
import org.example.ex.OfferConflictException;
import org.example.exception.NotFoundException;
import org.example.models.Model;
import org.example.models.Offer;
import org.example.models.Users;
import org.example.repo.OfferRepository;
import org.example.repo.RoleRepository;
import org.example.service.OfferService;
import org.example.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String offer = "offer";

    @Override
    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream().map((s) -> modelMapper.map(s, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public OfferDto registerOffer(OfferDto offer) {

        if(!this.validationUtil.isValid(offer)) {
            this.validationUtil
                    .violations(offer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments in Offer!");
        }

        Offer o = modelMapper.map(offer, Offer.class);
        String offerId = o.getId();
        if (offerId == null || offerRepository.findById(offerId).isEmpty()) {
            return modelMapper.map(offerRepository.save(o), OfferDto.class);
        } else {
            throw new NotFoundException("A offer with this id already exists");
        }
    }

    @Override
    public void deleteOffer(String offerID) {
        Offer offer = offerRepository.findById(offerID)
                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + offerID));
        offerRepository.delete(offer);
    }

    @Override
    public OfferDto updateOffer(String offerID, OfferDto updateOffer) {
        Offer existingOffer = offerRepository.findById(offerID).orElseThrow(() -> new NotFoundException("Could not find" + offer + " by id: " + offerID));

        Model existingModel = modelMapper.map(updateOffer.getModel(), Model.class);
        Users existingUser = modelMapper.map(updateOffer.getUsers(), Users.class);

        existingOffer.setModel(existingModel);
        existingOffer.setUsers(existingUser);

//        existingOffer.setId(updateOffer.getId());
        existingOffer.setDescription(updateOffer.getDescription());
        existingOffer.setEngine(updateOffer.getEngine());
        existingOffer.setImageUrl(updateOffer.getImageURL());
        existingOffer.setMileage(updateOffer.getMileage());
        existingOffer.setPrice(updateOffer.getPrice());
        existingOffer.setTransmission(updateOffer.getTransmission());
        existingOffer.setYear(updateOffer.getYear());
        existingOffer.setCreated(updateOffer.getCreated());
        existingOffer.setModified(updateOffer.getModified());

        Offer savedOffer = offerRepository.save(existingOffer);
        return modelMapper.map(savedOffer, OfferDto.class);
    }

    public List<ShowOfferInfoDto> allOffer() {
        return offerRepository.findAll().stream().map(model -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());
    }

}
