package com.scaler.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/* Notes:
* Multiple Product objects will exist, so spring should not create this object for us. Hence, not using Component tag.
* Use lambock to write Getter/Setter getid() etc are automatically added.
* */
@Getter
@Setter
@Entity // to specify that its a table
public class  Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne
    private Category category; // in DB we will save categoryID instead, obviously
}
