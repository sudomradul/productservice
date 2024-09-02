package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* interface as per JPA guidelines */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // save is a ORM convention
    Product save(Product product); // this method is enough to save a product into the DB - it will also return a pdt.

    List<Product> findAll(); // two queries are fired here
    /*Hibernate: select p1_0.id,p1_0.category_id,p1_0.created_at,p1_0.description,p1_0.image_url,p1_0.is_deleted,p1_0.price,p1_0.title,p1_0.updated_at from product p1_0
    Hibernate: select c1_0.id,c1_0.category,c1_0.created_at,c1_0.is_deleted,c1_0.name,c1_0.updated_at from category c1_0 where c1_0.id=?*/
    // a dev might need more control over this - dont use ORMs blindly.

    Optional<Product> findById(Long id);
}
