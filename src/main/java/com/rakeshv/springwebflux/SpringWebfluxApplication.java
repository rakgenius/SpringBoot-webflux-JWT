package com.rakeshv.springwebflux;

import com.rakeshv.springwebflux.model.Product;
import com.rakeshv.springwebflux.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {


        WebClientApi api = new WebClientApi();

        api.postNewProduct()
                .thenMany(api.getAllProducts())
                .take(1)
                .flatMap(p -> api.updateProduct(p.getId(), "White tea", 0.99))
                .flatMap(p -> api.deleteProduct(p.getId()))
                .thenMany(api.getAllEventProducts());

        SpringApplication.run(SpringWebfluxApplication.class, args);

    }

    @Bean
    CommandLineRunner init(ProductRepository repository) {
        return args -> {
            Flux<Product> productFlux =
                Flux.just(
                    Product.builder().name("Big latte").price(2.99).build(),
                    Product.builder().name("Big decaf").price(2.49).build(),
                    Product.builder().name("Green tea").price(1.99).build()
                ).flatMap(p -> repository.save(p));

            productFlux.thenMany(repository.findAll())
                        .subscribe(System.out::println);
        };
    }
}
