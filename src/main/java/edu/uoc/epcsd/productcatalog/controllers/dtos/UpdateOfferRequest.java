package edu.uoc.epcsd.productcatalog.controllers.dtos;

import edu.uoc.epcsd.productcatalog.entities.OfferStatus;

public class UpdateOfferRequest {
    private OfferStatus status;

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }
}
