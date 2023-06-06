package com.vagai.salesorder.caller;

import com.vagai.salesorder.dto.product.ProductDto;
import com.vagai.salesorder.dto.product.ReserveProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "stock-product", url = "${app.product-service.host}")
public interface ProductCaller {

    @PostMapping(value = "${app.product-service.product.reserve}", produces = APPLICATION_JSON_VALUE)
    ProductDto reserveProductItem(ReserveProductDto reserveProductDto);

    @PostMapping(value = "${app.product-service.products.reserve-fixing-n-plus-one}", produces = APPLICATION_JSON_VALUE)
    List<ProductDto> reserveProductItemFixingNPlusOne(List<ReserveProductDto> reserveProductDto);

    @GetMapping(value = "${app.product-service.product.getById}", produces = APPLICATION_JSON_VALUE)
    ProductDto getProductById(@PathVariable("productId") Long productId);

    @PostMapping(value = "${app.product-service.products.getByIds}", produces = APPLICATION_JSON_VALUE)
    List<ProductDto> getProductsByIds(List<Long> productsId);

}
