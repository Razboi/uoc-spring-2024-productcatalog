package edu.uoc.epcsd.productcatalog.controllers.dtos;

import edu.uoc.epcsd.productcatalog.entities.ItemStatus;

public class UpdateItemRequest {
    private ItemStatus status;

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
