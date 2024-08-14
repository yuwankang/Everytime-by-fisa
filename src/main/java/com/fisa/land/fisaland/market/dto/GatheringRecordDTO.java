package com.fisa.land.fisaland.market.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatheringRecordDTO {
    private Long id;
    private Long userId;
    private Long gatheringRecordInfoId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
