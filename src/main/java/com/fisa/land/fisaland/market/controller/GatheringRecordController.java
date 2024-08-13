package com.fisa.land.fisaland.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
import com.fisa.land.fisaland.market.service.GatheringRecordService;

@RestController
@RequestMapping("market")
public class GatheringRecordController {
	
	@Autowired
	GatheringRecordService gatheringRecordService;
	
	@PostMapping("/gathering")
	public Long saveGatheringRecord(@RequestBody GatheringRecordInfoDTO.setGatheringRecord gatheringRecordInfoDto) {
		return gatheringRecordService.saveGatheringRecord(gatheringRecordInfoDto);
	}

//	@GetMapping("/gatherings")
//	public Long getGatherings(GatheringRecordInfoDTO.setGatheringRecord gatheringRecordInfoDto) {
//		return gatheringRecordService.saveGatheringRecord(gatheringRecordInfoDto);
//	}
}
