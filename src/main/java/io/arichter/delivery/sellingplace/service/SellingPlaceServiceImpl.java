package io.arichter.delivery.sellingplace.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceRepository;
import io.arichter.delivery.sellingplace.payload.MultiPolygonRequest;
import io.arichter.delivery.sellingplace.payload.SellingPlaceRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellingPlaceServiceImpl implements SellingPlaceService {

    private SellingPlaceRepository sellingPlaceRepository;

    public SellingPlaceServiceImpl(SellingPlaceRepository sellingPlaceRepository) {
        this.sellingPlaceRepository = sellingPlaceRepository;
    }

    @Override
    public String create(SellingPlaceRequest sellingPlaceRequest) {

        SellingPlace sellingPlace = new SellingPlace();
        sellingPlace.setTradingName(sellingPlaceRequest.getTradingName());
        sellingPlace.setOwnerName(sellingPlaceRequest.getOwnerName());
        sellingPlace.setDocument(sellingPlaceRequest.getDocument());
        //sellingPlace.setCoverageArea(sellingPlaceRequest.getCoverageArea());
        //sellingPlace.setAddress(sellingPlaceRequest.getAddress());

        sellingPlaceRepository.save(sellingPlace);

        return sellingPlace.getId();
    }

    @Override
    public SellingPlace getSellingPlace(String id) {
        return sellingPlaceRepository.findById(id).orElse(null);
    }

    @Override
    public List<SellingPlace> getSellingPlaces() {
        return sellingPlaceRepository.findAll();
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

                    sellingPlaceRequest.getDocument();
                }
            }
        }
    }

    private MultiPolygon buildMultiPolygon(MultiPolygonRequest multiPolygonRequest) {
        List<List<Double>> doubles = new ArrayList<>(multiPolygonRequest.getCoordinates().get(0).get(0).get(0));

        MultiPolygon multiPolygon = MultiPolygon.fromLngLats();
    }
}
