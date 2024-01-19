package com.demo.productservice.controller;

import com.demo.productservice.controller.api.ProductApi;
import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductDeleteResponse;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.platform.common.RequestMapper;
import com.demo.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ProductResponse createProduct(CreateUpdateProductRequest createUpdateProductRequest) {

        logger.info("Starting createProduct");
        ProductResponse productResponse
                = productService.createProduct(createUpdateProductRequest);
        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(String id, CreateUpdateProductRequest createUpdateProductRequest) {
        logger.info("Starting updateProduct");

        createUpdateProductRequest.setProductId(id);
        ProductResponse productResponse
                = productService.updateProduct(createUpdateProductRequest);
        return productResponse;
    }

    @Override
    public ProductDeleteResponse deleteProduct(String id) {

        logger.info("Starting deleteProduct");
        ProductDeleteResponse productDeleteResponse
                = productService.deleteProduct(id);
        return productDeleteResponse;
    }

    @Override
    public List<ProductResponse> findProductByName(String name) {
        return null;
    }

    @Override
    public ProductResponse findProductById(String id) {
        logger.info("Starting findProductById id {}", id);
        ProductResponse productResponse
                = productService.findProductById(id);
        return productResponse;
    }

    @Override
    public Page<ProductResponse> findAllProducts(Pageable page) {
        return null;
    }
}
