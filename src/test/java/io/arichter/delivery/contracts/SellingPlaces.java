package io.arichter.delivery.contracts;

import io.arichter.delivery.multipolygon.MultiPolygon;
import io.arichter.delivery.point.Point;
import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceController;
import io.arichter.delivery.sellingplace.service.SellingPlaceService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellingPlaces {

    @Mock
    private SellingPlaceService sellingPlaceService;

    @Before
    public void setup() {
        SellingPlace sellingPlace = new SellingPlace();
        sellingPlace.setId("25");
        sellingPlace.setDocument("20.053.623/0001-30");
        sellingPlace.setTradingName("Bar Legal");
        sellingPlace.setOwnerName("Daniel Henrique");
        sellingPlace.setCoverageArea(null);
        sellingPlace.setAddress(null);


        when(sellingPlaceService.getSellingPlaceByCoordinates(1D, 2D)).thenReturn(sellingPlace);

        RestAssuredMockMvc.standaloneSetup(new SellingPlaceController(sellingPlaceService));
    }
}
