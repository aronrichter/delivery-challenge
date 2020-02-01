package io.arichter.delivery.sellingplace;

import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Polygon;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Getter
@Setter
public class SellingPlace {

    @Id
    @GeneratedValue
    private Integer id;

    private String tradingName;
    private String ownerName;
    private String document;
    private MultiPolygon coverageArea;
    private Polygon address;
}
