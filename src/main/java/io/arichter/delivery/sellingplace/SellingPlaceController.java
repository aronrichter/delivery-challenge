package io.arichter.delivery.sellingplace;

import io.arichter.delivery.sellingplace.exception.NotFoundException;
import io.arichter.delivery.sellingplace.service.SellingPlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("selling_places")
public class SellingPlaceController {

    private SellingPlaceService sellingPlaceService;

    public SellingPlaceController(SellingPlaceService sellingPlaceService) {
        this.sellingPlaceService = sellingPlaceService;
    }

    /*
    @PostMapping
    public ResponseEntity<Void> create(SellingPlaceRequest sellingPlaceRequest) {
        String id = sellingPlaceService.create(sellingPlaceRequest);

        return ResponseEntity.created(URI.create(id)).build();
    }
    */

    @GetMapping("{id}")
    public SellingPlace getSellingPlace(@PathVariable String id) {
        SellingPlace sellingPlace = sellingPlaceService.getSellingPlace(id);

        if (sellingPlace == null) {
            throw new NotFoundException();
        }

        return sellingPlace;
    }

    @GetMapping
    public List<SellingPlace> getSellingPlaces() {
        return sellingPlaceService.getSellingPlaces();
    }

    @GetMapping("latitude/{latitude}/longitude/{longitude}")
    public SellingPlace getSellingPlacesByCoordinates(@PathVariable Double latitude,
                                                      @PathVariable Double longitude) {
        return sellingPlaceService.getSellingPlaceByCoordinates(latitude, longitude);
    }

}
