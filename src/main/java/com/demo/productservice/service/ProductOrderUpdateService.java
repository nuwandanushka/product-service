package com.demo.productservice.service;

import com.demo.productservice.dto.OrderProductDto;

/**
 * Define Product order update service methods.
 */
public interface ProductOrderUpdateService {

    /**
     * Make sure all products are available in store as per order.
     *
     * @param orderProductDto the order product dto
     */
    void updateProductDetails(OrderProductDto orderProductDto);
}
