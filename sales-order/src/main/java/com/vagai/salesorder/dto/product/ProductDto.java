package com.vagai.salesorder.dto.product;

import java.math.BigDecimal;

public record ProductDto(Long productId,
                         String name,
                         BigDecimal unitPrice,
                         BigDecimal qtdTotal,
                         BigDecimal qtdAvailable,
                         BigDecimal qtdReserved) {
}
