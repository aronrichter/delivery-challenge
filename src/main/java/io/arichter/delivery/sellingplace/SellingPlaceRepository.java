package io.arichter.delivery.sellingplace;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellingPlaceRepository extends MongoRepository<SellingPlace, String> {
}
