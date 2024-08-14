package com.fisa.land.fisaland.lending.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.land.fisaland.lending.entity.LendingRecordInfo.LendingStatus;
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

	@Override
    public Integer getTotalOverdueFeesByBorrower(Long borrowerId) {
        List<LendingRecords> lendingRecords = getLendingRecordsByBorrower(borrowerId);
        return lendingRecords.stream()
                .map(LendingRecords::getLendingRecordInfo)
                .filter(info -> info != null && info.getOverdueFee() != null)
                .mapToInt(LendingRecordInfo::getOverdueFee)
                .sum();
    }

	@Transactional
	@Override
    public LendingRecordInfo updateLendingRecordStatus(Long lendingRecordId, LendingRecordInfo.LendingStatus newStatus) {
        // 대여 기록을 조회
        Optional<LendingRecords> lendingRecordOptional = lendingRecordsRepository.findById(lendingRecordId);

        if (lendingRecordOptional.isPresent()) {
            LendingRecords lendingRecords = lendingRecordOptional.get();
            LendingRecordInfo lendingRecordInfo = lendingRecords.getLendingRecordInfo();

            if (lendingRecordInfo != null) {
                // 상태를 업데이트
                lendingRecordInfo.setStatus(newStatus);
                return lendingRecordsRepository.save(lendingRecords).getLendingRecordInfo();
            } else {
                throw new RuntimeException("LendingRecordInfo not found for the given LendingRecordId");
            }
        } else {
            throw new RuntimeException("LendingRecord not found for the given LendingRecordId");
        }
    }

}
