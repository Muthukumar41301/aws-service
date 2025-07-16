package com.aws.demo.aws_integration_service.service;

import com.aws.demo.aws_integration_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    public Product addProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Optional<Product> getProductById(int id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Product> updateProduct(int id, Product productDetails) {
        Optional<Product> optionalProduct = getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(int id) {
        return productList.removeIf(p -> p.getId() == id);
    }
}
