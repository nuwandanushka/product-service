package com.demo.productservice.service.impl;

import com.demo.productservice.dto.OrderProductDto;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.entity.ProductEntity;
import com.demo.productservice.entity.enums.OrderProductStatus;
import com.demo.productservice.entity.enums.OrderStatus;
import com.demo.productservice.platform.common.CacheName;
import com.demo.productservice.repository.ProductRepository;
import com.demo.productservice.service.ProductOrderUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderUpdateService {

    private final Logger logger = LoggerFactory.getLogger(ProductOrderServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void updateProductDetails(OrderProductDto orderProductDto) {

        logger.info("updateProductDetails request dto {}", orderProductDto);

        orderProductDto.getList().stream().forEach(productDto -> {
            Optional<ProductEntity> optionalProductEntity = productRepository
                    .findByProductId(UUID.fromString(productDto.getProductId()));
            if (optionalProductEntity.isPresent()) {
                ProductEntity productEntity = optionalProductEntity.get();

                // check products available to fulfill this order
                if (optionalProductEntity.get().getQuantity() >= productDto.getQuantity()) {
                    productEntity.setQuantity(
                            productEntity.getQuantity() - productDto.getQuantity());
                    productRepository.save(productEntity);
                    updateCache(productEntity);
                    productDto.setStatus(OrderProductStatus.ACCEPTED.name());
                } else {
                    productDto.setStatus(OrderProductStatus.INSUFFICIENT.name());
                }
            } else {
                productDto.setStatus(OrderProductStatus.UNAVAILABLE.name());
            }
        });

        boolean allProductAccepted = orderProductDto.getList().stream().allMatch(
                productDto -> OrderProductStatus.ACCEPTED.name().equals(productDto.getStatus()));

        // Update order status
        if (allProductAccepted) {
            orderProductDto.setStatus(OrderStatus.ACCEPTED.name());
        }

        logger.info("final results for the product orders {}", orderProductDto);
    }

    private void updateCache(ProductEntity productEntity) {

        Cache cache = cacheManager.getCache(CacheName.PRODUCT_RESPONSE);

        if (cache != null) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(productEntity.getProductId().toString());
            productResponse.setCreatedDate(productEntity.getCreationDate());
            productResponse.setCreatedBy(productEntity.getCreatedBy());
            productResponse.setUpdatedDate(productEntity.getLastModifiedDate());
            productResponse.setCreatedBy(productEntity.getLastModifiedBy());
            productResponse.setQuantity(productEntity.getQuantity());
            productResponse.setPrice(productEntity.getPrice());

            cache.put(productEntity.getProductId().toString(), productResponse);
        }
    }
}
