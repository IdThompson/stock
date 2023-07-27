package com.activEgde.stock_Application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "currentPrice is required")
    private BigDecimal currentPrice;
}
