package com.fisa.fisaland.lending.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LendingRequestDto {
    private Long productId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
    private Boolean paid;
}
