package com.poc.reactiveprogramming.repository;

import com.poc.reactiveprogramming.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {
}
