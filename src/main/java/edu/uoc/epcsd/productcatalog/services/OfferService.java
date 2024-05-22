package edu.uoc.epcsd.productcatalog.services;

import edu.uoc.epcsd.productcatalog.controllers.dtos.CreateOfferRequest;
import edu.uoc.epcsd.productcatalog.controllers.dtos.UpdateOfferRequest;
import edu.uoc.epcsd.productcatalog.entities.Offer;
import edu.uoc.epcsd.productcatalog.entities.OfferStatus;
import edu.uoc.epcsd.productcatalog.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer createOffer(CreateOfferRequest createOfferRequest) {
        Offer newOffer = Offer.builder()
                .serialNumber(createOfferRequest.getSerialNumber())
                .userId(createOfferRequest.getUserId())
                .dailyPrice(createOfferRequest.getDailyPrice())
                .brand(createOfferRequest.getBrand())
                .model(createOfferRequest.getModel())
                .status(OfferStatus.PENDING)
                .date(LocalDate.now())
                .build();
        return offerRepository.save(newOffer);
    }

    public Offer updateOffer(String serialNumber, UpdateOfferRequest updateOfferRequest) {
        Offer offer = offerRepository.findById(serialNumber).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found")
        );
        offer.setStatus(updateOfferRequest.getStatus());
        return offerRepository.save(offer);
    }

    public List<Offer> listOffers(Long userId) {
        if (userId != null) {
            return offerRepository.findAllByUserId(userId);
        }
        return offerRepository.findAll();
    }
}
