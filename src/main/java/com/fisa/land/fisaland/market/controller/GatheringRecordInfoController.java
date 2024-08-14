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

import java.util.*;

@RestController
@RequestMapping("market")
public class GatheringRecordInfoController {
	
	@Autowired
	GatheringRecordInfoService gatheringRecordInfoService;
	
	@PostMapping("/gathering")
	public Long saveGatheringRecord(@RequestBody GatheringRecordInfoDTO.setGatheringRecordInfo gatheringRecordInfoDto) {
		return gatheringRecordInfoService.saveGatheringRecordInfo(gatheringRecordInfoDto);
	}

	@GetMapping("/gatherings")
	public List<GatheringRecordInfoDTO.getGatheringRecordInfo> getGatherings() {
		return gatheringRecordInfoService.getGatheringRecordInfo();
	}
	
	@GetMapping("/gathering/{gatheringId}")
	public GatheringRecordInfoDTO.getGatheringRecordInfoDetail getGatheringById(@PathVariable("gatheringId") Long gatheringId) {
		return gatheringRecordInfoService.getGatheringRecordInfoDetail(gatheringId);
	}
	
	@PutMapping("/gathering/{gatheringId}")
	public Long updateGatheringById(@PathVariable("gatheringId") Long gatheringId, @RequestBody GatheringRecordInfoDTO.updateGatheringRecordInfo updateGatheringRecordInfo) {
		return gatheringRecordInfoService.updateGatheringRecordInfo(gatheringId, updateGatheringRecordInfo);
	}
	
	@DeleteMapping("/gathering/{gatheringId}")
	public Long deleteGatheringById(@PathVariable("gatheringId") Long gatheringId) {
		return gatheringRecordInfoService.deleteGatheringRecordInfo(gatheringId);
	}
	
	@GetMapping("/{userId}/gatherings")
	public GatheringRecordInfoDTO.getMyGatheringList getMyList(@PathVariable("userId")Long userId){
		return gatheringRecordInfoService.getMyGatheringList(userId);
	}
}
