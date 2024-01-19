package com.demo.productservice.entity;

import com.demo.productservice.dto.type.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class ProductEntity extends Auditable {

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
