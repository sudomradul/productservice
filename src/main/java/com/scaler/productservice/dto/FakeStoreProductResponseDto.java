package com.scaler.productservice.dto;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

/* Note: to interface with the fake store responses - to map JSON resp of FakeStore APIs to java objects - we are fine with tight coupling here
* We will use it to pupulate stuff */

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private Long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    /* note: somewhere the logic to convert from FStoreProduct to Product also needs to exist. One such option is a method here, second is a mapper class */
    public Product toProduct()
    {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setPrice(Double.valueOf(this.price));
        product.setDescription(this.description);

        product.setImageUrl(this.image);

        Category category1 = new Category();
        category1.setName(this.category);

        product.setCategory(category1);

        return product;
    }
}
