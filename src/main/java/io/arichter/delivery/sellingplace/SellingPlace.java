package io.arichter.delivery.sellingplace;

import io.arichter.delivery.multipolygon.MultiPolygon;
import io.arichter.delivery.point.Point;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class SellingPlace {

    @Id
    private String id;

    @Indexed(unique = true)
    private String document;

    private String tradingName;
    private String ownerName;
    private MultiPolygon coverageArea;
    private Point address;
}
