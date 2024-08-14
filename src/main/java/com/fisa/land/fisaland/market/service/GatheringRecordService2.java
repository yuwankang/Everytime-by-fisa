package com.fisa.land.fisaland.market.service;

import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;

import java.util.List;

public interface GatheringRecordService2 {

    Long joinGathering(Long userId, Long gatherRecordId);
    
    GatheringRecordInfo getGatheringRecord(Long gatherRecordId);
    
    GatheringRecordInfo updateGatheringRecord(Long gatherRecordId, GatheringRecordInfo gatheringRecordInfo);
    
    void deleteGatheringRecord(Long gatherRecordId);

    List<GatheringRecordInfo> getGatheringRecordByUserId(Long userId);
    
    //GatheringRecordInfo getGatheringRecordInfoByUserId(Long userId);
}
