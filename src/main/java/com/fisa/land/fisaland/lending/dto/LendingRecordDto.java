package com.fisa.land.fisaland.lending.dto;

import java.time.LocalDateTime;

import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendingRecordDto {
    private Long productId;
    private Long borrowerId;
    private Long ownerId;
    private Integer fee;
}