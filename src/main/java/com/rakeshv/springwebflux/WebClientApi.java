package com.rakeshv.springwebflux;

import com.rakeshv.springwebflux.model.Product;
import com.rakeshv.springwebflux.model.ProductEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientApi {
    private WebClient webClient;

    WebClientApi() {
        //this.webClient = WebClient.create("http://localhost:8080/products");
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/products").build();
    }

    public Mono<ResponseEntity<Product>> postNewProduct() {
        return webClient.post()
                .body(Mono.just(new Product(null, "Green tea", 1.99)), Product.class)
                .exchange()
                .flatMap(response -> response.toEntity(Product.class))
                .doOnSuccess(o -> System.out.println("********POST " + o));
    }

    public Flux<Product> getAllProducts() {
        return webClient
                .get()
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnNext(o -> System.out.println(o));
    }

    public Mono<Product> updateProduct(String id, String name, Double price) {
        return webClient
                .put()
                .uri("/{id}", id)
                .body(Mono.just(new Product(null, name, price)), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnSuccess(o -> System.out.println(o));
    }

    public Mono<Void> deleteProduct(String id) {
        return webClient
                .delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(o -> System.out.println(o));
    }

    public Flux<Product> getAllEventProducts() {
        return webClient
                .get()
                .uri("/listall")
                .retrieve()
                .bodyToFlux(Product.class);
    }
}
