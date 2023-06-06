package com.vagai.salesproducts.mapper;

import com.vagai.salesproducts.dto.ProductDto;
import com.vagai.salesproducts.entity.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper
public interface ProductMapper {

    ProductDto mapToProductDto(Product product);

    Product mapToProduct(ProductDto productDto);
}
