package io.arichter.delivery.sellingplace.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import io.arichter.delivery.multipolygon.MultiPolygon;
import io.arichter.delivery.point.Point;
import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceRepository;
import io.arichter.delivery.sellingplace.exception.CoordinateNullException;
import io.arichter.delivery.sellingplace.payload.PdvsRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellingPlaceServiceImpl implements SellingPlaceService {

    private SellingPlaceRepository sellingPlaceRepository;


    public SellingPlaceServiceImpl(SellingPlaceRepository sellingPlaceRepository) {
        this.sellingPlaceRepository = sellingPlaceRepository;
    }

    @Override
    public String create(SellingPlace sellingPlace) {
        sellingPlaceRepository.save(sellingPlace);

        return sellingPlace.getId();
    }

    @Override
    public SellingPlace getSellingPlace(String id) {
        return sellingPlaceRepository.findById(id).orElse(null);
    }

    @Override
    public SellingPlace getSellingPlaceByCoordinates(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new CoordinateNullException();
        }

        com.vividsolutions.jts.geom.Point point =
                new GeometryFactory().createPoint(new Coordinate(latitude, longitude));

        List<SellingPlace> sellingPlaces = getSellingPlaces().stream()
                .filter(s -> buildCoverageArea(s.getCoverageArea()).contains(point))
                .collect(Collectors.toList());

        if (sellingPlaces.isEmpty()) {
            throw new CoordinateNullException();
        }

        return checkNearestSellingPlaceByPoint(sellingPlaces, point);
    }

    @Override
    public List<SellingPlace> getSellingPlaces() {
        return sellingPlaceRepository.findAll();
    }

    private SellingPlace checkNearestSellingPlaceByPoint(List<SellingPlace> sellingPlaces,
                                                         com.vividsolutions.jts.geom.Point point) {
        Double distance = Double.MAX_VALUE;
        SellingPlace nearestSellingPlace = null;

        for (SellingPlace sellingPlace : sellingPlaces) {
            com.vividsolutions.jts.geom.Point pointDto = buildAdressPoint(sellingPlace.getAddress());
            if (pointDto.distance(point) < distance) {
                distance = pointDto.distance(point);
                nearestSellingPlace = sellingPlace;
            }
        }

        return nearestSellingPlace;
    }

    private com.vividsolutions.jts.geom.MultiPolygon buildCoverageArea(MultiPolygon multiPolygon) {
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate[] coordinates = multiPolygon.getCoordinates().stream()
                .findFirst().get().stream()
                .findFirst().get().stream()
                .map(c -> new Coordinate(c.get(0), c.get(1))).toArray(Coordinate[]::new);

        LinearRing linearRing = geometryFactory.createLinearRing(coordinates);

        Polygon polygon = new GeometryFactory().createPolygon(linearRing);

        return new GeometryFactory().createMultiPolygon(new Polygon[]{polygon});
    }

    private com.vividsolutions.jts.geom.Point buildAdressPoint(Point point) {
        Double latitude = point.getCoordinates().get(0);
        Double longitude = point.getCoordinates().get(1);

        return new GeometryFactory().createPoint(new Coordinate(latitude, longitude));
    }

    @EventListener(ApplicationReadyEvent.class)
    private void importJson() throws FileNotFoundException {
        FileReader jsonFile = new FileReader("pdvs.json");

        Gson gson = new GsonBuilder().create();

        JsonStreamParser p = new JsonStreamParser(jsonFile);

        while (p.hasNext()) {
            JsonElement e = p.next();
            if (e.isJsonObject()) {
                PdvsRequest pdvsRequest = gson.fromJson(e, PdvsRequest.class);

                pdvsRequest.getPdvs().forEach(this::create);
            }
        }
    }
}
