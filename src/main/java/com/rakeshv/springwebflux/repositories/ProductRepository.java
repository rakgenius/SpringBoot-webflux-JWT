package com.rakeshv.springwebflux.repositories;

import com.rakeshv.springwebflux.model.Product;
import java.util.concurrent.Flow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findByName(Flow.Publisher<String> name);
}
