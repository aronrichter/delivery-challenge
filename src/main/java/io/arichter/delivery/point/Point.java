package io.arichter.delivery.point;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Point {

    private String type;
    private List<Double> coordinates;
}
