package com.fisa.land.fisaland.market.service;

import java.util.List;

import com.fisa.land.fisaland.market.dto.GatheringRecordDTO;

import jakarta.transaction.Transactional;

public interface GatheringRecordService {
	
    Long joinGathering(Long userId, Long gatheringRecordInfoId);
    
    void deleteGatheringRecord(GatheringRecordDTO.JoinRequest gatheringRecordId);

    List<GatheringRecordDTO> getGatheringRecordsByUserId(Long userId);
    
}
