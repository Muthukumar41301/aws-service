package com.aws.demo.aws_integration_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "updateStockProcedure",
                procedureName = "update_stock",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "productId",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "quantity",type = Integer.class)
                }
        )
})

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    private String name;
    private Double price;
    private int stockQuantity;
}
