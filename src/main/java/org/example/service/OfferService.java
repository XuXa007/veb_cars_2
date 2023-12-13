package org.example.service;

import jakarta.annotation.PostConstruct;
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
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    public ShowOfferInfoDto offerDetails(String offerId) {
        return modelMapper.map(offerRepository.findById(offerId).orElse(null), ShowOfferInfoDto.class);
    }

    @CacheEvict(cacheNames = "offer", allEntries = true)
    public void addOffer(AddOfferDto offer, String username) {
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());
        Offer of = modelMapper.map(offer, Offer.class);
        of.setModel(modelRepository.findByName(offer.getModels()).orElse(null));
        of.setUsers(usersRepository.findByUserName(username).orElse(null));
        offerRepository.saveAndFlush(of);
    }

    @Cacheable("offer")
    public List<ShowOfferInfoDto> getAll() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                .map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "offer", allEntries = true)
    public void removeOffer(String id) {
        offerRepository.deleteById(id);
    }

    @PostConstruct
    public void initializeTypeMap() {
        TypeMap<Offer, ShowOfferInfoDto> typeMap = modelMapper.createTypeMap(Offer.class, ShowOfferInfoDto.class);
        typeMap.addMapping(src -> src.getUsers().getUserName(), ShowOfferInfoDto::setUsers);
    }

    @Cacheable("offer")
    public List<ShowOfferInfoDto> getAllSortedByMileage(String sortBy, String sortOrder) {
        List<Offer> offers = offerRepository.findAll();

        List<ShowOfferInfoDto> offerInfos = offers.stream()
                .map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());

        offerInfos.sort((o1, o2) -> {
            int comparison;
            switch (sortBy.toLowerCase()) {
                case "mileage":
                    comparison = o1.getMileage() - o2.getMileage();
                    break;
                default:
                    comparison = 0;
            }

            return sortOrder.equalsIgnoreCase("desc") ? -comparison : comparison;
        });

        return offerInfos;
    }

}
