package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.models.Product;

import java.util.List;

/* Note: we dont want tight coupling of product service concrete class with the application*/
public interface ProductService {

    public Product getProductById(long id) throws ProductNotFoundException;
    // note: lets return Product from create product
    public Product createProduct(String title, String description, Double price, String imageUrl, String category);
    public void partialUpdate(Long id, Product product) throws ProductNotFoundException;
    public List<Product> getAllProducts();
}
