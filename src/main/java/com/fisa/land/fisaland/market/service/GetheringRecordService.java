package com.fisa.land.fisaland.market.service;

import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface GetheringRecordService {

	Long saveGetheringRecord(GatheringRecordInfoDTO.setGatheringRecord gethreingRecord);
}
