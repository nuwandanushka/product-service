package com.demo.productservice.controller;

import com.demo.productservice.BaseProductServiceApplicationTest;
import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends BaseProductServiceApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetProduct() throws Exception {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(UUID.randomUUID().toString());
        productResponse.setProductType("BOOK");
        productResponse.setDescription("Art Book");
        productResponse.setPrice(10.0);
        productResponse.setQuantity(4);
        productResponse.setCreatedBy("systemUser");
        productResponse.setCreatedDate(new Date());
        productResponse.setUpdatedDate(new Date());
        productResponse.setModifiedBy("systemUser");

        when(productService.findProductById(anyString())).thenReturn(productResponse);

        mockMvc.perform(get("/services/product/search-by-product/{id}", productResponse.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productResponse.getId()));;
    }

    @Test
    public void testCreateProduct() throws Exception {

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
        productResponse.setId(UUID.randomUUID().toString());


        when(productService.createProduct(any(CreateUpdateProductRequest.class))).thenReturn(productResponse);

        mockMvc.perform(post("/services/product")
                        .content(objectMapper.writeValueAsString(createUpdateProductRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateProduct() throws Exception {

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
        productResponse.setId(UUID.randomUUID().toString());

        when(productService.updateProduct(any(CreateUpdateProductRequest.class))).thenReturn(productResponse);

        mockMvc.perform(put("/services/product/{id}", UUID.randomUUID().toString())
                        .content(objectMapper.writeValueAsString(createUpdateProductRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
