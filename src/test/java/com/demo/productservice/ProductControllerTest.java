package com.demo.productservice;

import com.demo.productservice.controller.ProductController;
import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetUsers() throws Exception {

        CreateUpdateProductRequest createUpdateProductRequest = new CreateUpdateProductRequest();
        createUpdateProductRequest.setProductType("BOOK");
        createUpdateProductRequest.setDescription("Art Book");
        createUpdateProductRequest.setPrice(10.0);
        createUpdateProductRequest.setQuantity(4);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductType("BOOK");
        productResponse.setDescription("Art Book");
        productResponse.setPrice(10.0);
        productResponse.setQuantity(4);
        productResponse.setCreatedBy("systemUser");
        productResponse.setCreatedDate(new Date());


        when(productService.createProduct(any(CreateUpdateProductRequest.class))).thenReturn(productResponse);

        mockMvc.perform(post("/services/product")
                        .content(objectMapper.writeValueAsString(createUpdateProductRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
