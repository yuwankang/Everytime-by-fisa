package com.fisa.land.fisaland.market.service;

import java.util.*;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface GatheringRecordInfoService {

	Long saveGatheringRecordInfo(GatheringRecordInfoDTO.setGatheringRecordInfo gatheringRecordInfo);
	
	List<GatheringRecordInfoDTO.getGatheringRecordInfo> getGatheringRecordInfo();
	
	GatheringRecordInfoDTO.getGatheringRecordInfoDetail getGatheringRecordInfoDetail(Long gatheringRecordId);
	
	Long updateGatheringRecordInfo(Long gatheringRecordInfoId, GatheringRecordInfoDTO.updateGatheringRecordInfo updateGatheringRecordInfo);
	
	Long deleteGathringRecordInfo(Long gatheringRecordInfoId);
}
