package com.fisa.land.fisaland.lending.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecords;
import com.fisa.land.fisaland.lending.repository.LendingRecordRepository;

import jakarta.transaction.Transactional;

@Service
public class LendingRecordServiceImpl implements LendingRecordService{

	@Autowired
    private LendingRecordRepository lendingRecordsRepository;
	
	@Transactional
	@Override
	public LendingRecords saveLendingRecord(LendingRecordDto lendingRecordInfoDTO) {
		// LendingRecords 엔티티 생성 및 설정
        LendingRecords lendingRecords = LendingRecords.createLendingRecords(lendingRecordInfoDTO);
        return lendingRecordsRepository.save(lendingRecords);
	}

	@Override
	public List<LendingRecords> getLendingRecordsByBorrower(Long borrowerId) {
		return lendingRecordsRepository.findByBorrowerId(borrowerId);
	}

	@Override
	public List<LendingRecords> getLendingRecordsByOwner(Long ownerId) {
		return lendingRecordsRepository.findByOwnerId(ownerId);
	}

}
