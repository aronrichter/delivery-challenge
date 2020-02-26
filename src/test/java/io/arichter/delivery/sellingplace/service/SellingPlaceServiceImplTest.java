package io.arichter.delivery.sellingplace.service;

import io.arichter.delivery.sellingplace.SellingPlace;
import io.arichter.delivery.sellingplace.SellingPlaceRepository;
import io.arichter.delivery.sellingplace.exception.SellingPlaceAlreadyExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SellingPlaceServiceImplTest {

    @InjectMocks
    private SellingPlaceServiceImpl sellingPlaceService;

    @Mock
    private SellingPlaceRepository sellingPlaceRepository;

    @Test(expected = SellingPlaceAlreadyExistsException.class)
    public void shouldThrowSellingPlaceAlreadyExistsException() {
        when(sellingPlaceRepository.findByDocument("1")).thenReturn(new SellingPlace());

        sellingPlaceService.validadeDocument("1");
    }
}