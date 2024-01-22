package com.demo.productservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderProductDto {

    private String orderNumber;

    private List<ProductDto> list;

    private String status;

    /**
     * The type Product dto.
     */
    @Data
    public static class ProductDto {
        private String productId;
        private int quantity;
        private String status;
    }

}
