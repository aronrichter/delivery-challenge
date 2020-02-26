package io.arichter.delivery.sellingplace.payload;

import io.arichter.delivery.sellingplace.SellingPlace;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PdvsRequest {

    List<SellingPlace> pdvs;
}
