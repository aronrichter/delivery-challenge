package io.arichter.delivery.sellingplace;

import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class SellingPlace {

    @Id
    private String id;

    private String tradingName;
    private String ownerName;
    private String document;
    private MultiPolygon coverageArea;
    private Point address;
}
