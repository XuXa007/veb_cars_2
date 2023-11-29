package org.example.service;

import org.example.dtos.*;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    List<OfferDto> getAllOffers();

    OfferDto registerOffer(OfferDto offerDto);
    void deleteOffer(String offerID);

    OfferDto updateOffer(String offerID, OfferDto updateOffer);

    List<ShowOfferInfoDto> allOffers();

    ShowOfferInfoDto offerDetails(String offerId);

    void addOffer(AddOfferDto offerModel);
}
