package com.fisa.fisaland.lending.service;

import com.fisa.fisaland.lending.dto.LendingRequestDto;
import com.fisa.fisaland.lending.dto.ReturnRequestDto;
import com.fisa.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.fisaland.lending.repository.LendingRecordInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingService {
    @Autowired
    private LendingRecordInfoRepository lendingRecordInfoRepository;

    public void createLending(LendingRequestDto requestDto) {
        LendingRecordInfo lendingRecord = new LendingRecordInfo();
        lendingRecord.setBorrow_id(requestDto.getStartDate());
        lendingRecord.setReturn_date(requestDto.getEndDate());
        lendingRecord.setFee(requestDto.getTotalPrice().intValue());
        lendingRecord.setStatus("rented");
        // Set additional fields
        lendingRecordInfoRepository.save(lendingRecord);
    }

    public void requestReturn(ReturnRequestDto requestDto) {
        LendingRecordInfo record = lendingRecordInfoRepository.findById(requestDto.getRentalId()).orElseThrow();
        record.setStatus("return_requested");
        lendingRecordInfoRepository.save(record);
    }

    public void approveReturn(Long rentalId) {
        LendingRecordInfo record = lendingRecordInfoRepository.findById(rentalId).orElseThrow();
        record.setStatus("return_completed");
        lendingRecordInfoRepository.save(record);
    }
}
