package com.fisa.land.fisaland.market.dto;

public class GatheringRecordDTO {

    public static class JoinRequest {
        private Long userId;
        private Long gatheringRecordInfoId;

        // Getters and Setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getGatheringRecordInfoId() {
            return gatheringRecordInfoId;
        }

        public void setGatheringRecordInfoId(Long gatheringRecordInfoId) {
            this.gatheringRecordInfoId = gatheringRecordInfoId;
        }
    }

}
