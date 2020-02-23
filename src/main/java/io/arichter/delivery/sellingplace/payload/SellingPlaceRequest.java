package io.arichter.delivery.sellingplace.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellingPlaceRequest {

    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private MultiPolygonRequest coverageArea;
    private PointRequest address;
}
