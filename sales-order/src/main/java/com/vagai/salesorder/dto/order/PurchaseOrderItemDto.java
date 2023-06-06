package com.vagai.salesorder.dto.order;

import java.math.BigDecimal;

public record PurchaseOrderItemDto(Long purchaseOrderItemId,
                                   Long productId,
                                   BigDecimal quantity,
                                   BigDecimal priceItem,
                                   BigDecimal totalPrice) {
}
