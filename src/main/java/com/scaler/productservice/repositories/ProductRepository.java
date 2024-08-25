package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/* interface as per JPA guidelines */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product); // this method is enough to save a product into the DB - it will also return a pdt.
}
