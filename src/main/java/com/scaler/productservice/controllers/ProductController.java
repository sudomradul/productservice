package com.scaler.productservice.controllers;

import com.scaler.productservice.dto.ProductResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id)
    {
        ProductResponseDto dummy = new ProductResponseDto(1L, "abc", "dummy pdt dto", 100.0, "www.google.com", "Apparel");
        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<ProductResponseDto>(dummy, HttpStatusCode.valueOf(202));
        return responseEntity;
    }

    @GetMapping("/product")
    public String getAllProducts()
    {
        return "Hello World";
    }

    public void createProduct()
    {

    }

    public void deleteProduct()
    {

    }
}
