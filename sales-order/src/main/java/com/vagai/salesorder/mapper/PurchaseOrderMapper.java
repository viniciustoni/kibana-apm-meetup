package com.vagai.salesorder.mapper;


import com.vagai.salesorder.caller.ProductCaller;
import com.vagai.salesorder.dto.order.PurchaseOrderDto;
import com.vagai.salesorder.dto.order.PurchaseOrderItemDto;
import com.vagai.salesorder.dto.product.ProductDto;
import com.vagai.salesorder.entity.PurchaseOrder;
import com.vagai.salesorder.entity.PurchaseOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

@Mapper
public abstract class PurchaseOrderMapper {

    @Autowired
    private ProductCaller productCaller;

    public abstract PurchaseOrderDto mapToPurchaseOrderDto(PurchaseOrder purchaseOrder);

    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "purchaseOrderItems", ignore = true)
    public abstract PurchaseOrder mapToPurchaseOrderWithoutAmountAndOrderItems(PurchaseOrderDto purchaseOrderDto);

    @Mapping(target = "purchaseOrder", source = "purchaseOrder")
    @Mapping(target = "productId", source = "productDto.productId")
    @Mapping(target = "priceItem", source = "productDto.unitPrice")
    @Mapping(target = "totalPrice", expression = "java(productDto.unitPrice().multiply(purchaseOrderItemDto.quantity()))")
    abstract PurchaseOrderItem mapToPurchaseOrderItem(PurchaseOrder purchaseOrder, PurchaseOrderItemDto purchaseOrderItemDto, ProductDto productDto);

    public PurchaseOrder mapToPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = mapToPurchaseOrderWithoutAmountAndOrderItems(purchaseOrderDto);

        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderDto.purchaseOrderItems()
                .stream()
                .map(purchaseOrderItemDto -> mapToPurchaseOrderItem(purchaseOrder, purchaseOrderItemDto))
                .toList();
        BigDecimal totalAmount = purchaseOrderItems.stream()
                .map(PurchaseOrderItem::getTotalPrice)
                .reduce(ZERO, BigDecimal::add);

        purchaseOrder.setTotalAmount(totalAmount);
        purchaseOrder.setPurchaseOrderItems(purchaseOrderItems);

        return purchaseOrder;
    }

    private PurchaseOrderItem mapToPurchaseOrderItem(PurchaseOrder purchaseOrder, PurchaseOrderItemDto purchaseOrderItemDto) {
        ProductDto productDto = productCaller.getProductById(purchaseOrderItemDto.productId());
        return mapToPurchaseOrderItem(purchaseOrder, purchaseOrderItemDto, productDto);
    }

    public PurchaseOrder mapToPurchaseOrderFixNPlusOneRestCall(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = mapToPurchaseOrderWithoutAmountAndOrderItems(purchaseOrderDto);

        List<PurchaseOrderItem> purchaseOrderItems = mapToPurchaseOrderItemFixNPlusOneRestCall(purchaseOrder, purchaseOrderDto.purchaseOrderItems());
        BigDecimal totalAmount = purchaseOrderItems.stream()
                .map(PurchaseOrderItem::getTotalPrice)
                .reduce(ZERO, BigDecimal::add);

        purchaseOrder.setTotalAmount(totalAmount);
        purchaseOrder.setPurchaseOrderItems(purchaseOrderItems);

        return purchaseOrder;
    }

    private List<PurchaseOrderItem> mapToPurchaseOrderItemFixNPlusOneRestCall(PurchaseOrder purchaseOrder, List<PurchaseOrderItemDto> purchaseOrderItemDtos) {

        List<Long> productIds = purchaseOrderItemDtos.stream()
                .map(PurchaseOrderItemDto::productId)
                .toList();
        Map<Long, ProductDto> productDtoMap = productCaller.getProductsByIds(productIds)
                .stream()
                .collect(Collectors.toMap(ProductDto::productId, Function.identity()));

        return purchaseOrderItemDtos.stream()
                .map(purchaseOrderItemDto -> mapToPurchaseOrderItem(purchaseOrder, purchaseOrderItemDto, productDtoMap))
                .toList();
    }

    private PurchaseOrderItem mapToPurchaseOrderItem(PurchaseOrder purchaseOrder, PurchaseOrderItemDto purchaseOrderItemDto, Map<Long, ProductDto> productDtoMap) {
        ProductDto productDto = productDtoMap.get(purchaseOrderItemDto.productId());
        return mapToPurchaseOrderItem(purchaseOrder, purchaseOrderItemDto, productDto);
    }
}
