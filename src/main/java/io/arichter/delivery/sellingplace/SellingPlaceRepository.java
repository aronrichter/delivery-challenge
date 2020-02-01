package io.arichter.delivery.sellingplace;

import com.mapbox.geojson.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellingPlaceRepository extends JpaRepository<SellingPlace, Integer> {

    List<SellingPlace> findByAddress(Polygon address);
}
