package com.demo.productservice.repository;

import com.demo.productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Declare Product repository methods.
 */

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    /**
     * Find by product id optional.
     *
     * @param productId the product id
     * @return the optional
     */
    Optional<ProductEntity> findByProductId(UUID productId);
}
