package io.arichter.delivery.sellingplace.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiPolygonRequest {

    private String type;
    private List<List<List<List<Double>>>> coordinates;
}
