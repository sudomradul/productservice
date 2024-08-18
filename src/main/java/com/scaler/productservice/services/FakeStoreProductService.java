package com.scaler.productservice.services;

/* Note: Services should be generic methods - keep your services as generic as possible - dont couple with a specific DTO
* Spring says work with Java as much as possible. - java does not support json inherently, so we need to map the api resp to java object
* */

import com.scaler.productservice.dto.FakeStoreProductRequestDto;
import com.scaler.productservice.dto.FakeStoreProductResponseDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*RestTemplate - has many methods
* we use these methods for JSON - JAva interoperability for eg.
* */

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    @Autowired // Note: field injections are not suggested so we do with method, reasons aplenty (unknown to me)
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
        // this.restTemplate = new RestTemplate(); - to handle this manually but we let Spring control it but RestTemplate is not autowired so spring does not know about RestTemplate - we need @configuration
    }

    /* Note: this is an API call being made to FakeAPI store to get a response */
    @Override
    public Product getProductById(long id) {
        FakeStoreProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductResponseDto.class
        );

        return responseDto.toProduct();
    }

    // Note: type erasure in generics during runtime creates trouble here so we use array instead of List<>
    @Override
    public List<Product> getAllProducts()
    {
        FakeStoreProductResponseDto[] responseDTos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductResponseDto[].class
        );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductResponseDto responseDto: responseDTos) {
            products.add(responseDto.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName)
    {
        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setPrice(price);
        requestDto.setImage(imageUrl);
        requestDto.setCategory(categoryName);

        FakeStoreProductResponseDto responseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreProductResponseDto.class // will contain ID - see fakestoreapi
        );

        return responseDto.toProduct();
    }
}
