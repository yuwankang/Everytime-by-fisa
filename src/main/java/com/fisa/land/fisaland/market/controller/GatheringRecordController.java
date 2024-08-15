package com.fisa.land.fisaland.market.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.market.dto.GatheringRecordDTO;
import com.fisa.land.fisaland.market.service.GatheringRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/market")
@Tag(name = "모임 기록 API", description = "모임 참가 신청, 취소 및 특정 유저의 모임 리스트를 조회하는 API")
public class GatheringRecordController {

    @Autowired
    private GatheringRecordService gatheringRecordService;

    @Operation(summary = "모임 참가 신청", description = "유저가 특정 모임에 참가 신청을 하는 API")
    @PostMapping("/gathering/join")
    public ResponseEntity<Long> joinGathering(@RequestBody GatheringRecordDTO.JoinRequest joinRequest) {
        Long gatheringRecordId = gatheringRecordService.joinGathering(joinRequest.getUserId(), joinRequest.getGatheringRecordInfoId());
        return ResponseEntity.ok(gatheringRecordId);
    }

    @Operation(summary = "모임 참가 취소", description = "유저가 참가한 모임을 취소하는 API")
    @DeleteMapping("/gathering/cancel/{gatheringRecordId}")
    public ResponseEntity<Void> cancelGathering(
            @Parameter(description = "모임 기록 ID", example = "1") @PathVariable("gatheringRecordId") Long gatheringRecordId) {
        gatheringRecordService.deleteGatheringRecord(gatheringRecordId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "특정 유저의 모임 리스트 조회", description = "특정 유저가 참가한 모든 모임 기록을 조회하는 API")
    @GetMapping("/gathering/user/{userId}")
    public ResponseEntity<List<GatheringRecordDTO>> getUserGatheringList(
            @Parameter(description = "유저 ID", example = "1") @PathVariable("userId") Long userId) {
        List<GatheringRecordDTO> gatheringList = gatheringRecordService.getGatheringRecordsByUserId(userId);
        return ResponseEntity.ok(gatheringList);
    }
}
