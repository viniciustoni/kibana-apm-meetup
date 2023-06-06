package com.vagai.salesproducts.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super(String.format("Product id [%s] not found.", productId));
    }
}
