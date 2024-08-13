package com.scaler.productservice.dto;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter /* these getters and setters will be used by spring to convert the class intro a json representation when serving requests */
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String category; // we only expose the string instead of whole Category object

    public ProductResponseDto()
    {}
    public static ProductResponseDto from(Product product)
    {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setCategory(product.getCategory().getName());
        productResponseDto.setImageUrl(product.getImageUrl());
        return productResponseDto;
    }
}
