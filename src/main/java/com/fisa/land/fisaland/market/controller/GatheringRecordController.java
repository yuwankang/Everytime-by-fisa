package com.fisa.land.fisaland.market.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fisa.land.fisaland.market.dto.GatheringRecordDTO;
import com.fisa.land.fisaland.market.dto.GatheringRecordDTO.JoinRequest;
import com.fisa.land.fisaland.market.service.GatheringRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/market")
@Tag(name = "모임 기록 API", description = "모임 참가 신청, 취소 및 특정 유저의 모임 리스트를 조회하는 API")
public class GatheringRecordController {

    private static final Logger logger = LoggerFactory.getLogger(GatheringRecordController.class);

    @Autowired
    private GatheringRecordService gatheringRecordService;

    @Operation(summary = "모임 참가 신청", description = "유저가 특정 모임에 참가 신청을 하는 API")
    @PostMapping("/gathering/join")
    public ResponseEntity<Long> joinGathering(@RequestBody GatheringRecordDTO.JoinRequest joinRequest) {
        Long gatheringRecordId = gatheringRecordService.joinGathering(joinRequest.getUserId(), joinRequest.getGatheringRecordInfoId());
        logger.info("User {} joined gathering {} on {} at {}", 
                    joinRequest.getUserId(), joinRequest.getGatheringRecordInfoId(), joinRequest.getVisitDate(), joinRequest.getVisitTime());
        return ResponseEntity.ok(gatheringRecordId);
    }

    @Operation(summary = "모임 참가 취소", description = "유저가 참가한 모임을 취소하는 API")
    @DeleteMapping("/gathering/cancel")
    public ResponseEntity<Void> cancelGathering(
            @RequestBody GatheringRecordDTO.JoinRequest gatheringRecordId) {
        gatheringRecordService.deleteGatheringRecord(gatheringRecordId);
        logger.info("Gathering record {} cancelled", gatheringRecordId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "특정 유저의 모임 리스트 조회", description = "특정 유저가 참가한 모든 모임 기록을 조회하는 API")
    @GetMapping("/gathering/user/{userId}")
    public ResponseEntity<List<GatheringRecordDTO>> getUserGatheringList(
            @PathVariable("userId") Long userId) {
        List<GatheringRecordDTO> gatheringList = gatheringRecordService.getGatheringRecordsByUserId(userId);
        logger.info("Fetched gathering list for user {}", userId);
        return ResponseEntity.ok(gatheringList);
    }
    
    @Operation(summary = "모임 참가자의 유저 ID 조회 및 상태 업데이트", description = "특정 모임의 모든 참가자 유저 ID를 조회하고 상태를 AFTER로 업데이트하는 API")
    @PatchMapping("/gathering/{gatheringRecordInfoId}/users")
    public ResponseEntity<List<Long>> getUserIdsAndUpdateStatus(
            @PathVariable("gatheringRecordInfoId") Long gatheringRecordInfoId) {
        List<Long> userIds = gatheringRecordService.getUserIdsAndUpdateStatus(gatheringRecordInfoId);
        logger.info("Fetched user IDs and updated status for gatheringRecordInfoId {}", gatheringRecordInfoId);
        return ResponseEntity.ok(userIds);
    }
}