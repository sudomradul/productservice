package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

/* Note: we dont want tight coupling of product service concrete class with the application*/
public interface ProductService {

    public Product getProductById(long id);
}
