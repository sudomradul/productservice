package com.scaler.productservice.dto;

import com.scaler.productservice.models.Category;
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
}
