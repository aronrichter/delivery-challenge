package io.arichter.delivery.sellingplace.service;

import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.payload.SellingPlaceRequest;

import java.util.List;

public interface SellingPlaceService {

    String create(SellingPlaceRequest sellingPlaceRequest);

    SellingPlace getSellingPlace(String id);

    List<SellingPlace> getSellingPlaces();
}
