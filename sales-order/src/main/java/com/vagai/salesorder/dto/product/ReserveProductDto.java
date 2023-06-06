package com.vagai.salesorder.dto.product;

import java.math.BigDecimal;

public record ReserveProductDto(Long productId,
                               BigDecimal qtdReserved) {
}
