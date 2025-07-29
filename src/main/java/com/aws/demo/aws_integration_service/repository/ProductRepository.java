package com.aws.demo.aws_integration_service.repository;

import com.aws.demo.aws_integration_service.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ProductRepository extends JpaRepository<Products,Integer> {

    @Procedure(name = "updateStockProcedure")
    void updateStockProcedure(Integer productId, Integer quantity);

    @Query(value = "SELECT get_total_price(:productId)",nativeQuery = true)
    Double getTotalPrice(Integer productId);
}
