package com.vagai.salesorder.dto.product;

import java.math.BigDecimal;

public record UpdateStockDto(Long productId,
                             BigDecimal qtdTotal,
                             BigDecimal qtdReserved,
                             BigDecimal qtdSold) {
}
