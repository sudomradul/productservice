package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productDbService")
// @Primary /* Primary service that implements the ProductService - the spring will prefer this above all */
// @Qualifier - use it with the name defined with the service tag above
public class ProductDBService implements ProductService {

    private ProductRepository productRepository;
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String category) {
        Product product = new Product();
        product.setTitle("randomtest");
        productRepository.save(product);

        return null;
    }

    @Override
    public void partialUpdate(Long id, Product product) {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
