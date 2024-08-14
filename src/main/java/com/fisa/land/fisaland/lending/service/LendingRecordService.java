package com.fisa.land.fisaland.lending.service;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecords;

public interface LendingRecordService {

	LendingRecords saveLendingRecord(LendingRecordDto lendingRecordInfoDTO);
}
