package com.vagai.salesproducts.dto;

import java.math.BigDecimal;

public record ReserveProductDto(Long productId, BigDecimal qtdReserved) {
}
