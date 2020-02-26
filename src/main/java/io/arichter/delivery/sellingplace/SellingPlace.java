package io.arichter.delivery.sellingplace;

import io.arichter.delivery.multipolygon.MultiPolygon;
import io.arichter.delivery.point.Point;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Getter
@Setter
public class SellingPlace {

    @Id
    private String id;

    @NotBlank(message = "documento deve ser infomado")
    private String document;

    @NotBlank(message = "nome do ponto de venda deve ser informado")
    private String tradingName;

    @NotBlank(message = "nome do proprietário deve ser infomado")
    private String ownerName;

    @NotBlank(message = "a área de cobertura deve ser informada")
    private MultiPolygon coverageArea;

    @NotBlank(message = "o endereço deve ser informado")
    private Point address;
}
