package com.fisa.land.fisaland.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GatheringRecordDTO {
    private Long gatheringRecordId;
    private Long userId;
    private Long gatheringRecordInfoId;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class JoinRequest {
        private Long userId;
        private Long gatheringRecordInfoId;
        private String visitDate; // 추가된 필드
        private String visitTime; // 추가된 필드
    }
}
