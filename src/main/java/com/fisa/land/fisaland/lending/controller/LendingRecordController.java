package com.fisa.land.fisaland.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecords;
import com.fisa.land.fisaland.lending.service.LendingRecordService;

@RestController
@RequestMapping("village/products")
public class LendingRecordController {

	@Autowired
	LendingRecordService lendingRecordService;
	
	//대여 등록
	@PostMapping("lendingRecord")
	public LendingRecords saveLendingRecord(@RequestBody LendingRecordDto lendingRecordDto) {
        return lendingRecordService.saveLendingRecord(lendingRecordDto);
    
	}
	
	//내가 빌린 대여 리스트 조회
    @GetMapping("/borrower/{borrowerId}")
    public List<LendingRecords> getLendingRecordsByBorrower(@PathVariable("borrowerId") Long borrowerId) {
        return lendingRecordService.getLendingRecordsByBorrower(borrowerId);
    }

	//내가 빌려준 대여 리스트 조회
    @GetMapping("/owner/{ownerId}")
    public List<LendingRecords> getLendingRecordsByOwner(@PathVariable("ownerId") Long ownerId) {
        return lendingRecordService.getLendingRecordsByOwner(ownerId);
    }

	//연체료 조회
	
	/* 반납 시 연체료 세팅 
	 * 밀린 날짜 * 2 * 대여료 
	 * */
	
	//대여 상태 수정
}
