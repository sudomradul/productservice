package com.scaler.productservice.controllers;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.dto.ErrorDto;
import com.scaler.productservice.dto.ProductRequestDto;
import com.scaler.productservice.dto.ProductResponseDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService, RestTemplate restTemplate){
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
    public ProductResponseDto getProductById(@PathVariable("id") Long id) throws ProductNotFoundException
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

    //TODO: implement this API
    public void deleteProduct()
    {
    }

    //TODO: implement this API
    public void partialUpdateProduct()
    {
    }

    /* This exception handler will be used to handle the exceptions that reach the level of productController. It will handle only those which are throws from controller.
    * This is to override spring's default exception handler. called by spring whenever a null pointer exception happens in any of the controller method */
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto nullPointerExceptionHandler()
    {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("something went wrong");
        errorDto.setStatus("FAILURE");
        return errorDto;
    }

    /*General: if your method knows how to handle a exception - catch it, otherwise throw it up the stack */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception)
    {
         ErrorDto errorDto = new ErrorDto();
         errorDto.setStatus("FAILURE");
         errorDto.setMessage(exception.getMessage());
         // lets return correct error code
        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
        return responseEntity;
    }
}
