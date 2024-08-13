package com.scaler.productservice.services;

/* Note: Services should be generic methods - keep your services as generic as possible - dont couple with a specific DTO
* Spring says work with Java as much as possible. - java does not support json inherently, so we need to map the api resp to java object
* */

import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    @Autowired // field injections are not suggested so we do with method, reasons aplenty (unknown to me)
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
        // this.restTemplate = new RestTemplate(); - to handle this manually but we let Spring control it but RestTemplate is not autowired so spring does not know about RestTemplate - we need @configuration
    }
    @Override
    public Product getProductById(long id) {

    }
}
