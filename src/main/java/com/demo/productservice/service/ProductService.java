package com.demo.productservice.service;

import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductDeleteResponse;
import com.demo.productservice.dto.ProductResponse;

/**
 * Declare Product service methods.
 */
public interface ProductService {
    /**
     * Create product.
     *
     * @param createUpdateProductRequest the create update product request
     * @return the product response
     */
    ProductResponse createProduct(CreateUpdateProductRequest createUpdateProductRequest);

    /**
     * Update product product response.
     *
     * @param createUpdateProductRequest the create update product request
     * @return the product response
     */
    ProductResponse updateProduct(CreateUpdateProductRequest createUpdateProductRequest);

    /**
     * Delete product product delete response.
     *
     * @param id the id
     * @return the product delete response
     */
    ProductDeleteResponse deleteProduct(String id);

    /**
     * Find product by id product response.
     *
     * @param id the id
     * @return the product response
     */
    ProductResponse findProductById(String id);
}
