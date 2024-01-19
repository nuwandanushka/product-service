package com.demo.productservice.validation;

/**
 * Declare Validator method.
 */
public interface Validator<T> {


    void validate(T source);

}
