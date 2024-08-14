package com.fisa.land.fisaland.lending.service;

import java.util.List;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecords;

public interface LendingRecordService {

	LendingRecords saveLendingRecord(LendingRecordDto lendingRecordInfoDTO);
	
	List<LendingRecords> getLendingRecordsByBorrower(Long borrowerId);
	
	List<LendingRecords> getLendingRecordsByOwner(Long ownerId);
}
