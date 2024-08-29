package com.scaler.productservice.services;

import com.scaler.productservice.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDbService")
// @Primary /* Primary service that implements the ProductService - the spring will prefer this above all */
// @Qualifier - use it with the name defined with the service tag above
public class ProductDBService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDBService(ProductRepository productRepository,
                            CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product createProduct(String title, String description, Double price, String imageUrl, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        product.setCategory(getCategoryFromDB(categoryName));

        return productRepository.save(product);
    }

    @Override
    public void partialUpdate(Long id, Product product) {

    }

    private Category getCategoryFromDB(String categoryName)
    {
        // optional - way to avoid null pointer exception if the category does not exist in DB
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        if (categoryOptional.isEmpty())
        {
            // create a new one - the save will make JPA save it in DB actually
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
