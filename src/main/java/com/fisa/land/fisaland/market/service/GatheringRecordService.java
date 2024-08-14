package com.fisa.land.fisaland.market.service;

import java.util.*;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface GatheringRecordService {

	Long saveGatheringRecord(GatheringRecordInfoDTO.setGatheringRecord gethreingRecord);
	
	List<GatheringRecordInfoDTO.getGatheringRecord> getGatheringRecord();
	
	GatheringRecordInfoDTO.getGatheringRecordDetail getGatheringRecordDetail(Long gatheringRecordId);
}
