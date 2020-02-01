package io.arichter.delivery.sellingplace.service;

import com.mapbox.geojson.MultiPolygon;
import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class SellingPlaceServiceImpl implements SellingPlaceService {

    private SellingPlaceRepository sellingPlaceRepository;

    public SellingPlaceServiceImpl(SellingPlaceRepository sellingPlaceRepository) {
        this.sellingPlaceRepository = sellingPlaceRepository;
    }

    @Override
    public SellingPlace create(SellingPlace sellingPlace) {
        return sellingPlaceRepository.save(sellingPlace);
    }

    @Override
    public SellingPlace getSellingPlace(Integer id) {
        return sellingPlaceRepository.findById(id).orElse(null);
    }
}
