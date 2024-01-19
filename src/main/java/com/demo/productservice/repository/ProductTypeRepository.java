package com.demo.productservice.repository;

import com.demo.productservice.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * The Product type repository.
 */
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, UUID> {


    /**
     * Find by product type optional.
     *
     * @param productType the product type
     * @return the optional
     */
    Optional<ProductTypeEntity> findByProductType(String productType);
}
