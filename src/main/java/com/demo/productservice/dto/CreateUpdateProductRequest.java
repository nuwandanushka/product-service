package com.demo.productservice.dto;

import com.demo.productservice.entity.ProductTypeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * The Create update product request.
 */
@Data
public class CreateUpdateProductRequest {

    @JsonIgnore
    private String productId;

    private String productType;

    private double price;

    private int quantity;

    private String description;

}
