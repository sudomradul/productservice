package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;

/* Note: To add the product to fake store api*/
@Getter
@Setter
public class FakeStoreProductRequestDto {
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
