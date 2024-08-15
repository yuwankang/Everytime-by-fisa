package com.fisa.land.fisaland.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
import com.fisa.land.fisaland.market.service.GatheringRecordInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("market")
@Tag(name = "모임 정보 API", description = "모임 정보 생성, 조회, 수정 및 삭제를 관리하는 API")
public class GatheringRecordInfoController {

	@Autowired
	private GatheringRecordInfoService gatheringRecordInfoService;

	@Operation(summary = "모임 정보 등록", description = "새로운 모임 정보를 등록하는 API")
	@PostMapping("/gathering")
	public Long saveGatheringRecord(@RequestBody GatheringRecordInfoDTO.setGatheringRecordInfo gatheringRecordInfoDto) {
		return gatheringRecordInfoService.saveGatheringRecordInfo(gatheringRecordInfoDto);
	}

	@Operation(summary = "모든 모임 정보 조회", description = "모든 모임의 정보를 조회하는 API")
	@GetMapping("/gatherings")
	public List<GatheringRecordInfoDTO.getGatheringRecordInfo> getGatherings() {
		return gatheringRecordInfoService.getGatheringRecordInfo();
	}

	@Operation(summary = "특정 모임 정보 조회", description = "ID를 기반으로 특정 모임의 세부 정보를 조회하는 API")
	@GetMapping("/gathering/{gatheringId}")
	public GatheringRecordInfoDTO.getGatheringRecordInfoDetail getGatheringById(
			@Parameter(description = "모임 ID", example = "1") @PathVariable("gatheringId") Long gatheringId) {
		return gatheringRecordInfoService.getGatheringRecordInfoDetail(gatheringId);
	}

	@Operation(summary = "모임 정보 수정", description = "ID를 기반으로 특정 모임 정보를 수정하는 API")
	@PutMapping("/gathering/{gatheringId}")
	public Long updateGatheringById(
			@Parameter(description = "모임 ID", example = "1") @PathVariable("gatheringId") Long gatheringId,
			@RequestBody GatheringRecordInfoDTO.updateGatheringRecordInfo updateGatheringRecordInfo) {
		return gatheringRecordInfoService.updateGatheringRecordInfo(gatheringId, updateGatheringRecordInfo);
	}

	@Operation(summary = "모임 정보 삭제", description = "ID를 기반으로 특정 모임 정보를 삭제하는 API")
	@DeleteMapping("/gathering/{gatheringId}")
	public Long deleteGatheringById(
			@Parameter(description = "모임 ID", example = "1") @PathVariable("gatheringId") Long gatheringId) {
		return gatheringRecordInfoService.deleteGatheringRecordInfo(gatheringId);
	}

	@Operation(summary = "특정 유저의 모임 리스트 조회", description = "특정 유저가 참여한 모임 리스트를 조회하는 API")
	@GetMapping("/{userId}/gatherings")
	public GatheringRecordInfoDTO.getMyGatheringList getMyList(
			@Parameter(description = "유저 ID", example = "1") @PathVariable("userId") Long userId) {
		return gatheringRecordInfoService.getMyGatheringList(userId);
	}
}
