package io.arichter.delivery.sellingplace.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceRepository;
import io.arichter.delivery.sellingplace.payload.SellingPlaceRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.*;

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

    @EventListener(ApplicationReadyEvent.class)
    private void importJson() throws FileNotFoundException {
        FileReader jsonFile = new FileReader("pdvs.json");

        Gson gson = new GsonBuilder().create();

        JsonStreamParser p = new JsonStreamParser(jsonFile);

        while (p.hasNext()) {
            JsonElement e = p.next();
            if (e.isJsonObject()) {
                SellingPlaceRequest sellingPlaceRequest = gson.fromJson(e, SellingPlaceRequest.class);

                if (sellingPlaceRepository.findById(sellingPlaceRequest.getId()).orElse(null) == null) {
                    SellingPlace sellingPlace = new SellingPlace();
                    
                    sellingPlaceRepository.save(sellingPlace);
                }
            }
        }
    }
}
