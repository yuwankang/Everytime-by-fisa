package com.fisa.fisaland.lending.service;

import com.fisa.fisaland.lending.dto.LendingRequestDto;
import com.fisa.fisaland.lending.dto.ReturnRequestDto;
import com.fisa.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.fisaland.lending.exception.CustomException;
import com.fisa.fisaland.lending.repository.LendingRecordInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingService {

    @Autowired
    private LendingRecordInfoRepository lendingRecordInfoRepository;

    public void createLending(LendingRequestDto requestDto) {
        LendingRecordInfo lendingRecord = new LendingRecordInfo();
        lendingRecord.setBorrow_id(requestDto.getStartDate().atStartOfDay());
        lendingRecord.setReturn_date(requestDto.getEndDate().atStartOfDay());
        lendingRecord.setFee(requestDto.getTotalPrice().intValue());
        lendingRecord.setStatus(LendingRecordInfo.LendingStatus.RENTED.name()); // 상태를 문자열로 저장
        // Set additional fields if needed
        lendingRecordInfoRepository.save(lendingRecord);
    }

    public void requestReturn(ReturnRequestDto requestDto) {
        LendingRecordInfo record = lendingRecordInfoRepository.findById(requestDto.getRentalId())
                .orElseThrow(() -> new CustomException("Rental record not found"));
        record.setStatus(LendingRecordInfo.LendingStatus.RETURN_REQUESTED.name()); // 상태를 문자열로 저장
        lendingRecordInfoRepository.save(record);
    }

    public void approveReturn(Long rentalId) {
        LendingRecordInfo record = lendingRecordInfoRepository.findById(rentalId)
                .orElseThrow(() -> new CustomException("Rental record not found"));
        record.setStatus(LendingRecordInfo.LendingStatus.RETURN_COMPLETED.name()); // 상태를 문자열로 저장
        lendingRecordInfoRepository.save(record);
    }
}
