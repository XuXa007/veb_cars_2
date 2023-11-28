package org.example.service;

import org.example.dtos.OfferDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.dtos.ShowOfferInfoDto;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    List<OfferDto> getAllOffers();

    OfferDto registerOffer(OfferDto offerDto);
    void deleteOffer(String offerID);

    OfferDto updateOffer(String offerID, OfferDto updateOffer);

    List<ShowOfferInfoDto> allOffer();

}
