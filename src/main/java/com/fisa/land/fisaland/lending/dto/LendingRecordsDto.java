package com.fisa.land.fisaland.lending.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendingRecordsDto {
    private Long lendingRecordId;
    private Long productId;
    private Long borrowerId;
    private Long ownerId;
    private LocalDateTime created;
}
