package com.demo.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Response for base entity object.
 *
 * @author priyal
 */
@Data
public class BaseEntityResponse {

    private String id;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted;

}
