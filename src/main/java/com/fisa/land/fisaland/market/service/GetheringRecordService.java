package com.fisa.land.fisaland.market.service;

import com.fisa.land.fisaland.market.dto.GetheringRecordDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface GetheringRecordService {

	Long saveGetheringRecord(GetheringRecordDTO gethreingRecord);
}
