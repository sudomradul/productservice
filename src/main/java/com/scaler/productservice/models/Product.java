package com.scaler.productservice.models;

import lombok.Getter;
import lombok.Setter;

/* Notes:
* Multiple Product objects will exist, so spring should not create this object for us. Hence, not using Component tag.
* Use lambock to write Getter/Setter getid() etc are automatically added.
* */
@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
