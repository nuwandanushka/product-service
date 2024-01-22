package com.demo.productservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Define Product type entity.
 */
@Entity
@Table(name = "product_type")
@Getter
@Setter
public class ProductTypeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID productTypeId;

    @Column(name = "type")
    private String productType;
}
