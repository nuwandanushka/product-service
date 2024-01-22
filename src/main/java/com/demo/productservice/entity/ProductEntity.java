package com.demo.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", foreignKey = @ForeignKey(name = "product_type_fk"))
    private ProductTypeEntity productType;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

}
