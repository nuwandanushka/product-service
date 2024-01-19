package com.demo.productservice.controller.api;

import com.demo.productservice.dto.CreateUpdateProductRequest;
import com.demo.productservice.dto.ProductDeleteResponse;
import com.demo.productservice.dto.ProductResponse;
import com.demo.productservice.platform.common.RequestMapper;
import com.demo.productservice.validation.message.Messages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The interface Product api.
 */
@Tag(name = "Product", description = "the Product Api")
public interface ProductApi extends BaseController {

    /**
     * Create product product response.
     *
     * @param createUpdateProductRequest the create update product request
     * @return the product response
     */
    @Operation(
            summary = "Create products",
            description = "create new products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Request data to create product")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ProductResponse createProduct(
            @RequestBody(required = true) CreateUpdateProductRequest createUpdateProductRequest);

    /**
     * Update product product response.
     *
     * @param id                         the id
     * @param createUpdateProductRequest the create update product request
     * @return the product response
     */
    @Operation(
            summary = "Update a product",
            description = "update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Request data to update a product for given Id")
    @PutMapping(value = RequestMapper.ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ProductResponse updateProduct(@PathVariable(value = "id") String id,
            @RequestBody(required = true) CreateUpdateProductRequest createUpdateProductRequest);

    /**
     * Delete product product delete response.
     *
     * @param id the id
     * @return the product delete response
     */
    @Operation(summary = "deletes a product", description = "deletes a product")
    @ApiResponse(responseCode = Messages.SUCCESS, description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProductDeleteResponse.class)))
    @DeleteMapping(value = RequestMapper.ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ProductDeleteResponse deleteProduct(@PathVariable(value = "id") String id);


    /**
     * Find product by name list.
     *
     * @param name the name
     * @return the list
     */
    @Operation(summary = "find product by product name",
            description = "find product by product name")
    @ApiResponse(responseCode = Messages.SUCCESS, description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    @GetMapping(value = RequestMapper.NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findProductByName(@PathVariable(value = "name") String name);


    /**
     * Find product by id product response.
     *
     * @param id the id
     * @return the product response
     */
    @Operation(summary = "find product by product Id",
            description = "find product by product Id")
    @ApiResponse(responseCode = Messages.SUCCESS, description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProductResponse.class)))
    @GetMapping(value = RequestMapper.BY_PRODUCT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findProductById(@PathVariable(value = "id") String id);

    /**
     * Find all products page.
     *
     * @param page the page
     * @return the page
     */
    @Operation(summary = "find all products", description = "find all products")
    @ApiResponse(responseCode = Messages.SUCCESS, description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    @GetMapping(value = RequestMapper.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> findAllProducts(Pageable page);

}
