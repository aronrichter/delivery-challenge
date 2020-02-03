package io.arichter.delivery.sellingplace.payload;

import com.mapbox.geojson.Point;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class SellingPlaceRequest {

    private Integer id;
    private String tradingName;
    private String ownerName;
    private String document;
    private MultiPolygonRequest coverageArea;
    private PointRequest address;
}
