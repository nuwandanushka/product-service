package com.demo.productservice.validation;

import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.entity.ProductEntity;
import com.demo.productservice.entity.ProductTypeEntity;
import com.demo.productservice.repository.ProductRepository;
import com.demo.productservice.repository.ProductTypeRepository;
import com.demo.productservice.validation.message.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

/**
 * Define Product update validator.
 */
@Service("productUpdateValidator")
public class ProductUpdateValidator implements Validator<CreateUpdateProductRequest> {

    private final Logger logger = LoggerFactory.getLogger(ProductCreateValidator.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public void validate(CreateUpdateProductRequest createUpdateProductRequest) {
        logger.info("start validating product update request");

        if (Optional.ofNullable(createUpdateProductRequest).isPresent()) {

            Optional<ProductTypeEntity> productType
                    = productTypeRepository.findByProductType(
                    createUpdateProductRequest.getProductType());

            Assert.isTrue(productType.isPresent(), Messages.PRODUCT_TYPE_NOT_AVAILABLE);

            Optional<ProductEntity> productEntity = productRepository.findByProductId(UUID.fromString(createUpdateProductRequest.getProductId()));

            Assert.isTrue(productEntity.isPresent(), String.format(Messages.PRODUCT_IS_NOT_AVAILABLE,
                    createUpdateProductRequest.getProductId()));

        } else {
            Assert.isTrue(false, Messages.REQUEST_IS_EMPTY);
        }

        logger.info("validating product update request end");
    }
}
