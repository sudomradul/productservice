package com.scaler.productservice.services;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;

import java.util.List;

/* Note: we dont want tight coupling of product service concrete class with the application*/
public interface ProductService {

    public Product getProductById(long id);
    // note: lets return Product from create product
    public Product createProduct(String title, String description, Double price, String imageUrl, String category);
    public List<Product> getAllProducts();
}
