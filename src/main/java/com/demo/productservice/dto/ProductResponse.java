package com.demo.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductResponse extends BaseEntityResponse {

    private String productId;

    private String productType;

    private double price;

    private int quantity;

    private String description;
}
