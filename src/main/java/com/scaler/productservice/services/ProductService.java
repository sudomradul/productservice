package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

import java.util.List;

/* Note: we dont want tight coupling of product service concrete class with the application*/
public interface ProductService {

    public Product getProductById(long id);

    public List<Product> getAllProducts();
}
