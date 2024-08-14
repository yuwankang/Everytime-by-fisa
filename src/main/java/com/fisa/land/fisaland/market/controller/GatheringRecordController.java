package com.fisa.land.fisaland.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.market.dto.GatheringRecordDTO;
import com.fisa.land.fisaland.market.service.GatheringRecordService;

@RestController
@RequestMapping("/gathering")
public class GatheringRecordController {

    @Autowired
    private GatheringRecordService gatheringRecordService;

    // 모임 참가 신청
    @PostMapping("/join/{userId}/{gatheringRecordInfoId}")
    public ResponseEntity<Long> joinGathering(@PathVariable Long userId, @PathVariable Long gatheringRecordInfoId) {
        Long gatheringRecordId = gatheringRecordService.joinGathering(userId, gatheringRecordInfoId);
        return ResponseEntity.ok(gatheringRecordId);
    }

    // 모임 참가 취소
    @DeleteMapping("/cancel/{gatheringRecordId}")
    public ResponseEntity<Void> cancelGathering(@PathVariable Long gatheringRecordId) {
        gatheringRecordService.deleteGatheringRecord(gatheringRecordId);
        return ResponseEntity.noContent().build();
    }

    // 특정 유저의 모임 리스트 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GatheringRecordDTO>> getUserGatheringList(@PathVariable Long userId) {
        List<GatheringRecordDTO> gatheringList = gatheringRecordService.getGatheringRecordsByUserId(userId);
        return ResponseEntity.ok(gatheringList);
    }
}
