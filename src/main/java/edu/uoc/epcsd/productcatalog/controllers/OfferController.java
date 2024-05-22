package edu.uoc.epcsd.productcatalog.controllers;

import edu.uoc.epcsd.productcatalog.controllers.dtos.CreateOfferRequest;
import edu.uoc.epcsd.productcatalog.controllers.dtos.UpdateOfferRequest;
import edu.uoc.epcsd.productcatalog.entities.Offer;
import edu.uoc.epcsd.productcatalog.services.OfferService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Offer> listOffers(@RequestParam(required = false) Long userId) {
        return offerService.listOffers(userId);
    }

    @PostMapping
    public ResponseEntity<String> createOffer(@RequestBody CreateOfferRequest createOfferRequest) {
        log.trace("createOffer");
        Offer newOffer = offerService.createOffer(createOfferRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{serialNumber}")
                .buildAndExpand(newOffer.getSerialNumber())
                .toUri();

        return ResponseEntity.created(uri).body(newOffer.getSerialNumber());
    }

    @PatchMapping("/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Offer> updateOffer(
            @PathVariable String serialNumber,
            @RequestBody UpdateOfferRequest updateOfferRequest
    ) {
        log.trace("updateOffer");
        Offer updatedOffer = offerService.updateOffer(serialNumber, updateOfferRequest);
        return ResponseEntity.ok().body(updatedOffer);
    }
}
