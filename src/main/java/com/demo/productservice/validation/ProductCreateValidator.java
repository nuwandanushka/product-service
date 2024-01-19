package com.demo.productservice.validation;

import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.entity.ProductEntity;
import com.demo.productservice.entity.ProductTypeEntity;
import com.demo.productservice.repository.ProductRepository;
import com.demo.productservice.repository.ProductTypeRepository;
import com.demo.productservice.validation.message.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

@Service("productCreateValidator")
public class ProductCreateValidator implements Validator<CreateUpdateProductRequest> {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public void validate(CreateUpdateProductRequest createUpdateProductRequest) {
        if (Optional.ofNullable(createUpdateProductRequest).isPresent()) {

            Optional<ProductTypeEntity> productType
                    = productTypeRepository.findByProductType(
                    createUpdateProductRequest.getProductType());

            Assert.isTrue(productType.isPresent(), Messages.PRODUCT_TYPE_NOT_AVAILABLE);

        }
    }
}
