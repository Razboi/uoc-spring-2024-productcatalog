package edu.uoc.epcsd.productcatalog.services;

import edu.uoc.epcsd.productcatalog.entities.Category;
import edu.uoc.epcsd.productcatalog.entities.Product;
import edu.uoc.epcsd.productcatalog.repositories.ItemRepository;
import edu.uoc.epcsd.productcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemRepository itemRepository;

    public List<Product> findAll(String name, String category) {
        if (name != null && category != null) {
            return productRepository.findByNameContainingAndCategoryNameContaining(name, category);
        }
        if (name != null) {
            return productRepository.findByNameContaining(name);
        }
        if (category != null) {
            return productRepository.findByCategoryNameContaining(category);
        }
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(Long categoryId, String name, String description, Double dailyPrice, String brand, String model) {

        Product product = Product.builder().name(name).description(description).dailyPrice(dailyPrice).brand(brand).model(model).build();

        if (categoryId != null) {
            Optional<Category> category = categoryService.findById(categoryId);

            if (category.isPresent()) {
                product.setCategory(category.get());
            }
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Optional<Product> productToDelete = productRepository.findById(productId);
        productToDelete.ifPresent(product -> {
            itemRepository.deleteAll(product.getItemList());
            productRepository.delete(product);
        });
    }
}
