package edu.uoc.epcsd.productcatalog.controllers.dtos;

import edu.uoc.epcsd.productcatalog.entities.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public final class CreateOfferRequest {

    private String serialNumber;

    private Long userId;

    private Double dailyPrice;

    private String brand;

    private String model;
}
