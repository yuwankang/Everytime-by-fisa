package com.fisa.land.fisaland.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.land.fisaland.lending.entity.LendingRecords;
import com.fisa.land.fisaland.lending.service.LendingRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("village/products")
@Tag(name = "대여 기록 API", description = "대여 기록을 등록, 조회, 수정하는 API")
public class LendingRecordController {

    @Autowired
    LendingRecordService lendingRecordService;

    @Operation(summary = "대여 기록 등록", description = "새로운 대여 기록을 등록하는 API")
    @PostMapping("lendingRecord")
    public LendingRecords saveLendingRecord(@RequestBody LendingRecordDto lendingRecordDto) {
        return lendingRecordService.saveLendingRecord(lendingRecordDto);
    }

    @Operation(summary = "내가 빌린 대여 목록 조회", description = "특정 대여자가 빌린 모든 대여 기록을 조회하는 API")
    @GetMapping("/borrower/{borrowerId}")
    public List<LendingRecords> getLendingRecordsByBorrower(
            @Parameter(description = "대여자 ID", example = "1") @PathVariable("borrowerId") Long borrowerId) {
        return lendingRecordService.getLendingRecordsByBorrower(borrowerId);
    }

    @Operation(summary = "내가 빌려준 대여 목록 조회", description = "특정 소유자가 빌려준 모든 대여 기록을 조회하는 API")
    @GetMapping("/owner/{ownerId}")
    public List<LendingRecords> getLendingRecordsByOwner(
            @Parameter(description = "소유자 ID", example = "1") @PathVariable("ownerId") Long ownerId) {
        return lendingRecordService.getLendingRecordsByOwner(ownerId);
    }

    @Operation(summary = "대여자의 총 연체료 조회", description = "특정 대여자가 현재까지 발생한 총 연체료를 조회하는 API")
    @GetMapping("/borrower/{borrowerId}/totalOverdueFee")
    public Integer getTotalOverdueFeeByBorrower(
            @Parameter(description = "대여자 ID", example = "1") @PathVariable("borrowerId") Long borrowerId) {
        return lendingRecordService.getTotalOverdueFeesByBorrower(borrowerId);
    }

    @Operation(summary = "대여 상태 수정", description = "특정 대여 기록의 상태를 수정하는 API")
    @PutMapping("/{lendingRecordId}/status")
    public LendingRecordInfo updateLendingRecordStatus(
            @Parameter(description = "대여 기록 ID", example = "1") @PathVariable("lendingRecordId") Long lendingRecordId) {
        return lendingRecordService.updateLendingRecordStatus(lendingRecordId);
    }
}
