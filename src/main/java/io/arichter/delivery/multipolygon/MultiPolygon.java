package io.arichter.delivery.multipolygon;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiPolygon {

    private String type;
    private List<List<List<List<Double>>>> coordinates;
}
