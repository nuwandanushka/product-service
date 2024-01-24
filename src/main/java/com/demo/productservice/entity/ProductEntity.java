package com.demo.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Define Product entity.
 */
@Entity
@Table(name = "product")
@Data
public class ProductEntity extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", foreignKey = @ForeignKey(name = "product_type_fk"))
    private ProductTypeEntity productType;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

}
