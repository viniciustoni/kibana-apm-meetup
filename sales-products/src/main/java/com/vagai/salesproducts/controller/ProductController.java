package com.vagai.salesproducts.controller;

import com.vagai.salesproducts.dto.ProductDto;
import com.vagai.salesproducts.dto.ReserveProductDto;
import com.vagai.salesproducts.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1", produces = APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping(value = "/products", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getProductsById(@RequestBody List<Long> productsId) {
        return ResponseEntity.ok(productService.getProductsByIds(productsId));
    }

    @PostMapping(value = "/product", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.createNewProduct(productDto));
    }

    @PostMapping(value = "/product/reserve", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> reserveProduct(@RequestBody ReserveProductDto reserveProductDto) {
        return ResponseEntity.ok(productService.reserveProduct(reserveProductDto));
    }

    @PostMapping(value = "/products/reserve-fixing-n-plus-one", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> reserveProductFixingNPlusOne(@RequestBody List<ReserveProductDto> reserveProductDtos) {
        return ResponseEntity.ok(productService.reserveProductFixingNPlusOne(reserveProductDtos));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/product/{productId}/slow")
    public ResponseEntity<ProductDto> getProductByIdSlowMethod(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductByIdSlow(productId));
    }
}
