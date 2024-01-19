package com.demo.productservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "product_type")
@Getter
@Setter
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID productTypeId;

    @Column(name = "type")
    private String productType;
}
