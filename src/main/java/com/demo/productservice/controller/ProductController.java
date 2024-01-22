package com.demo.productservice.controller;

import com.demo.productservice.controller.api.ProductApi;
import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductDeleteResponse;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.platform.common.CacheName;
import com.demo.productservice.platform.common.RequestMapper;
import com.demo.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RequestMapper.PRODUCT)
public class ProductController implements ProductApi {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Override
    @CachePut(cacheNames = CacheName.PRODUCT_RESPONSE, key = "#result.id")
    public ProductResponse createProduct(CreateUpdateProductRequest createUpdateProductRequest) {

        logger.info("Starting createProduct {}", createUpdateProductRequest);
        ProductResponse productResponse
                = productService.createProduct(createUpdateProductRequest);
        return productResponse;
    }

    @Override
    @CachePut(cacheNames = CacheName.PRODUCT_RESPONSE, key = "#result.id")
    public ProductResponse updateProduct(String id, CreateUpdateProductRequest createUpdateProductRequest) {
        logger.info("Starting updateProduct id: {} ::: request: {}", id, createUpdateProductRequest);

        createUpdateProductRequest.setProductId(id);
        ProductResponse productResponse
                = productService.updateProduct(createUpdateProductRequest);
        return productResponse;
    }

    @Override
    @CacheEvict(cacheNames = CacheName.PRODUCT_RESPONSE, key = "#id", beforeInvocation = true)
    public ProductDeleteResponse deleteProduct(String id) {

        logger.info("Starting deleteProduct id: {}", id);
        ProductDeleteResponse productDeleteResponse
                = productService.deleteProduct(id);
        return productDeleteResponse;
    }

    @Override
    public List<ProductResponse> findProductByName(String name) {
        return null;
    }

    @Override
    @Cacheable(value = CacheName.PRODUCT_RESPONSE, key = "#id")
    public ProductResponse findProductById(String id) {
        logger.info("Starting findProductById id {}", id);
        ProductResponse productResponse
                = productService.findProductById(id);
        return productResponse;
    }
}
