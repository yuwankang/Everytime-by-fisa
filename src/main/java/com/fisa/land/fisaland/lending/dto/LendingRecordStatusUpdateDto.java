package com.fisa.land.fisaland.lending.dto;

import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LendingRecordStatusUpdateDto {

    private Long lendingRecordId;  // 대여 기록 ID
    private LendingRecordInfo.LendingStatus status;  // 대여 상태
}
