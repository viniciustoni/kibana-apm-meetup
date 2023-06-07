package com.vagai.salesproducts.mapper;

import com.vagai.salesproducts.dto.ProductDto;
import com.vagai.salesproducts.entity.Product;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import java.util.stream.IntStream;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper
public interface ProductMapper {

    ProductDto mapToProductDto(Product product);

    @SneakyThrows
    default ProductDto mapToProductDtoSlow(Product product) {
        boolean shouldSlowDown = product.getProductId() % 2L == 0;
        if (shouldSlowDown) {
            Thread.sleep(3000L);
        }
        return mapToProductDto(product);
    }

    Product mapToProduct(ProductDto productDto);
}
