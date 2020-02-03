package io.arichter.delivery.sellingplace.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PointRequest {

    private String type;
    private List<Double> coordinates;
}
