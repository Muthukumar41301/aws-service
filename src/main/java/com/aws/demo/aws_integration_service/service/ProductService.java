package com.aws.demo.aws_integration_service.service;

import com.aws.demo.aws_integration_service.entity.Products;
import com.aws.demo.aws_integration_service.model.Product;
import com.aws.demo.aws_integration_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> productList = new ArrayList<>();
    private int nextId = 1;

    @Autowired
    private ProductRepository productRepository;

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

    public Products updateStockProcedure(Integer productId, Integer quantity) {
        productRepository.updateStockProcedure(productId,quantity);
        return productRepository.findById(productId).get();
    }

    public Double calculateProductPrice(Integer productId) {
        return productRepository.getTotalPrice(productId);
    }
}
