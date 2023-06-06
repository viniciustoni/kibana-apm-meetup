package com.vagai.salesorder.dto.order;

import java.math.BigDecimal;
import java.util.List;

public record PurchaseOrderDto(Long purchaseOrderId,
                               Long clientId,
                               BigDecimal totalAmount,
                               List<PurchaseOrderItemDto> purchaseOrderItems) {
}
