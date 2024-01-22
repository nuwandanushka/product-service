package com.demo.productservice.dto;

import lombok.Data;

@Data
public class ProductDeleteResponse {

    private String id;

    private boolean deleted;
}
