package com.poc.reactiveprogramming.controller;

import com.poc.reactiveprogramming.entity.Product;
import com.poc.reactiveprogramming.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    //Get all products
    @GetMapping
    public Flux<Product> getAllProducts() {

        return productRepository.findAll();
    }

    //Get a single product by ID
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {

        return productRepository.findById(id);
    }

    //Create a new product
    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    //Update an existing product
    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    return productRepository.save(existingProduct);
                });
    }

    //Delete a product by ID
    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productRepository.deleteById(id);
    }
}
