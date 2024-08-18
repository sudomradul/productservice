package com.scaler.productservice.controllers;

import com.scaler.productservice.dto.ProductRequestDto;
import com.scaler.productservice.dto.ProductResponseDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/*
* Notes: @RestController is a wrapper/alias of @Controller - which means spring will handle it for you.
* @GetMapping will call a particular method when the corresponding API endpoint is hit.
* @PathVariable: to map path variable to method parameter
* We return as DTO (data transfer object) instead of whole Model as it may have some variables that we may not want to expose those. Still, people do it.
* Spring converts the ProductResponseDto to a JSON representation while returning response - but ProductResponseDto must have @Getters implemented. - done via spring::jackson
* HTTP status are also like guidelines.
* You can return just DTO although we usually dont do that
*   - You return a whole HTTP request
*   - Way to do that is called: ResponseEntity <dev wants control over http return code etc> for eg in DTO code no exception -> 200 OK
*/
@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /* Option 1
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id)
    {
        ProductResponseDto dummy = new ProductResponseDto(1L, "abc", "dummy pdt dto", 100.0, "www.google.com", "Apparel");
        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<ProductResponseDto>(dummy, HttpStatusCode.valueOf(202));
        return responseEntity;
    }*/

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id)
    {
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product: products)
        {
            responseDtos.add(ProductResponseDto.from(product));
        }
        return responseDtos;
    }

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        // Note: It is possible that createProduct may need a different dto
        // to differentiate resp of getProduct and createProduct
        Product product = productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl(),
                productRequestDto.getCategoryName()
        );

        return ProductResponseDto.from(product);
    }

    public void deleteProduct()
    {

    }
}
