package com.demo.productservice.service.impl;

import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductDeleteResponse;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.entity.ProductEntity;
import com.demo.productservice.entity.ProductTypeEntity;
import com.demo.productservice.repository.ProductRepository;
import com.demo.productservice.repository.ProductTypeRepository;
import com.demo.productservice.service.ProductService;
import com.demo.productservice.validation.Validator;
import com.demo.productservice.validation.message.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

/**
 * Define implementation of Product service.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("productCreateValidator")
    private Validator productCreateValidator;

    @Autowired
    @Qualifier("productUpdateValidator")
    private Validator productUpdateValidator;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public ProductResponse createProduct(CreateUpdateProductRequest createUpdateProductRequest) {

        logger.info("Starting ProductServiceImpl#createProduct");

        productCreateValidator.validate(createUpdateProductRequest);

        Optional<ProductTypeEntity> productTypeEntityOptional = productTypeRepository.findByProductType(createUpdateProductRequest.getProductType());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductType(productTypeEntityOptional.get());
        productEntity.setPrice(createUpdateProductRequest.getPrice());
        productEntity.setQuantity(createUpdateProductRequest.getQuantity());
        productEntity.setDescription(createUpdateProductRequest.getDescription());

        ProductEntity productEntityCreated = productRepository.save(productEntity);

        logger.info("Successful ProductServiceImpl#createProduct");

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productEntityCreated.getProductId().toString());
        productResponse.setCreatedDate(productEntityCreated.getCreationDate());
        productResponse.setCreatedBy(productEntityCreated.getCreatedBy());

        return productResponse;

    }

    @Override
    public ProductResponse updateProduct(CreateUpdateProductRequest createUpdateProductRequest) {
        logger.info("Starting ProductServiceImpl#updateProduct");

        productUpdateValidator.validate(createUpdateProductRequest);

        Optional<ProductTypeEntity> productTypeEntityOptional = productTypeRepository.findByProductType(createUpdateProductRequest.getProductType());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductType(productTypeEntityOptional.get());
        productEntity.setPrice(createUpdateProductRequest.getPrice());
        productEntity.setQuantity(createUpdateProductRequest.getQuantity());
        productEntity.setDescription(createUpdateProductRequest.getDescription());

        ProductEntity productEntityCreated = productRepository.save(productEntity);

        logger.info("Successful ProductServiceImpl#updateProduct");

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productEntityCreated.getProductId().toString());
        productResponse.setCreatedDate(productEntityCreated.getCreationDate());
        productResponse.setCreatedBy(productEntityCreated.getCreatedBy());
        productResponse.setUpdatedDate(productEntityCreated.getLastModifiedDate());
        productResponse.setCreatedBy(productEntityCreated.getLastModifiedBy());

        return productResponse;
    }

    @Override
    public ProductDeleteResponse deleteProduct(String id) {

        logger.info("Starting ProductServiceImpl#deleteProduct");

        Optional<ProductEntity> productEntity = productRepository.findByProductId(UUID.fromString(id));

        Assert.isTrue(productEntity.isPresent(), String.format(Messages.PRODUCT_IS_NOT_AVAILABLE,
                id));

        productRepository.delete(productEntity.get());

        logger.info("Successful ProductServiceImpl#DeleteProduct");

        ProductDeleteResponse productDeleteResponse = new ProductDeleteResponse();
        productDeleteResponse.setDeleted(true);
        productDeleteResponse.setId(productEntity.get().getProductId().toString());
        return productDeleteResponse;
    }

    @Override
    public ProductResponse findProductById(String id) {
        logger.info("Starting ProductServiceImpl#findProductById");

        Optional<ProductEntity> productEntity
                = productRepository.findByProductId(UUID.fromString(id));

        Assert.isTrue(productEntity.isPresent(), String.format(Messages.PRODUCT_IS_NOT_AVAILABLE, id));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(productEntity.get().getProductId().toString());
        productResponse.setDescription(productEntity.get().getDescription());
        productResponse.setQuantity(productEntity.get().getQuantity());
        productResponse.setPrice(productEntity.get().getPrice());
        productResponse.setProductType(productEntity.get().getProductType().getProductType());

        return productResponse;
    }
}
