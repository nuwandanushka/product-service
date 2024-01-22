package com.demo.productservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductResponse implements Serializable {

    private String id;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted;

    private String productType;

    private double price;

    private int quantity;

    private String description;
}
