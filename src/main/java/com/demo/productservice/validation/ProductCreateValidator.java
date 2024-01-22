package com.demo.productservice.validation;

import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.entity.ProductTypeEntity;
import com.demo.productservice.repository.ProductTypeRepository;
import com.demo.productservice.validation.message.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Define Product create validator.
 */
@Service("productCreateValidator")
public class ProductCreateValidator implements Validator<CreateUpdateProductRequest> {

    private final Logger logger = LoggerFactory.getLogger(ProductCreateValidator.class);
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public void validate(CreateUpdateProductRequest createUpdateProductRequest) {
        logger.info("start validating product creation request");
        if (Optional.ofNullable(createUpdateProductRequest).isPresent()) {

            Optional<ProductTypeEntity> productType
                    = productTypeRepository.findByProductType(
                    createUpdateProductRequest.getProductType());

            Assert.isTrue(productType.isPresent(), Messages.PRODUCT_TYPE_NOT_AVAILABLE);

        }

        logger.info("validating product creation end");

    }
}
