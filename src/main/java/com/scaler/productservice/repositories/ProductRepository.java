package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* interface as per JPA guidelines */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // save is a ORM convention
    Product save(Product product); // this method is enough to save a product into the DB - it will also return a pdt.
}
