package com.vagai.salesproducts.dto;

import java.math.BigDecimal;

public record AddProductStockDto(Long productId, BigDecimal qtdAdded) {
}
