package com.aws.demo.aws_integration_service.controller;

import com.aws.demo.aws_integration_service.entity.Products;
import com.aws.demo.aws_integration_service.model.Product;
import com.aws.demo.aws_integration_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "CRUD operations for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Add a new product", description = "Creates a new product in the database")
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @Operation(summary = "Get all products", description = "Retrieves a list of all available products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Get product by ID", description = "Retrieves a single product by its unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update product", description = "Updates product details by product ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete product", description = "Deletes a product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update Stock Procedure", description = "Update the Stock Procedure")
    @PutMapping("/update/{productId}/{quantity}")
    public Products updateStockProcedure(@PathVariable Integer productId,
                                         @PathVariable Integer quantity){
        return productService.updateStockProcedure(productId,quantity);
    }

    @Operation(summary = "Product Total Price", description = "Product Total Price")
    @GetMapping("/total-price/{productId}")
    public Double getTotalPrice(@PathVariable Integer productId) {
        return productService.calculateProductPrice(productId);
    }
}
