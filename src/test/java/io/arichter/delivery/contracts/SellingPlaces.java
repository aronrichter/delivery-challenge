package io.arichter.delivery.contracts;

import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceController;
import io.arichter.delivery.sellingplace.service.SellingPlaceService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.mockito.Mockito.when;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellingPlaces {

    @Autowired
    private SellingPlaceController sellingPlaceController;

    @Mock
    private SellingPlaceService sellingPlaceService;

    @Before
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(sellingPlaceController);

        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

        when(sellingPlaceService.getSellingPlaceByCoordinates(-43.36556, -22.99669))
                .thenReturn(new SellingPlace());
    }
}
