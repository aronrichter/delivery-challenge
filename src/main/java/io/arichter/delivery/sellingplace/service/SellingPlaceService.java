package io.arichter.delivery.sellingplace.service;

import io.arichter.delivery.sellingplace.SellingPlace;

import java.util.List;

public interface SellingPlaceService {

    String create(SellingPlace sellingPlace);

    SellingPlace getSellingPlace(String id);

    SellingPlace getSellingPlaceByCoordinates(Double latitude, Double longitude);

    List<SellingPlace> getSellingPlaces();
}
