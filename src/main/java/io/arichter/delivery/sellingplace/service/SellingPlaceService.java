package io.arichter.delivery.sellingplace.service;

import io.arichter.delivery.sellingplace.SellingPlace;

public interface SellingPlaceService {

    SellingPlace create(SellingPlace sellingPlace);

    SellingPlace getSellingPlace(Integer id);
}
