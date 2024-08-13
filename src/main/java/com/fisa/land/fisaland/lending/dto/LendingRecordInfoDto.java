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
public class LendingRecordInfoDto {
    private Long lendingRecordId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private LocalDateTime actualReturnDate;
    private LendingRecordInfo.LendingStatus status;
    private Integer fee;
    private Integer overdueFee;


}