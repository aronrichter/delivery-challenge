package io.arichter.delivery.sellingplace;

import io.arichter.delivery.sellingplace.exception.NotFoundException;
import io.arichter.delivery.sellingplace.service.SellingPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("selling_places")
public class SellingPlaceController {

    private SellingPlaceService sellingPlaceService;

    public SellingPlaceController(SellingPlaceService sellingPlaceService) {
        this.sellingPlaceService = sellingPlaceService;
    }

    @PostMapping
    public ResponseEntity<Void> create(SellingPlace sellingPlace) {
        SellingPlace sellingPlaceCreated = sellingPlaceService.create(sellingPlace);

        return ResponseEntity.created(URI.create(sellingPlaceCreated.getId().toString())).build();
    }

    @GetMapping("{id}")
    public SellingPlace getSellingPlace(@PathVariable Integer id) {
        SellingPlace sellingPlace = sellingPlaceService.getSellingPlace(id);

        if (sellingPlace == null) {
            throw new NotFoundException();
        }

        return sellingPlace;
    }
}
