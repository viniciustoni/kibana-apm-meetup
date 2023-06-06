package com.vagai.salesorder.mapper;

import com.vagai.salesorder.dto.product.ReserveProductDto;
import com.vagai.salesorder.entity.PurchaseOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReserveProductDtoMapper {

    @Mapping(target = "qtdReserved", source = "quantity")
    ReserveProductDto mapToReserveProductDto(PurchaseOrderItem purchaseOrderItem);

}
