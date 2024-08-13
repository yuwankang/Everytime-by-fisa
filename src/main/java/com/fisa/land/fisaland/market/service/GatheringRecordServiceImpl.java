package com.fisa.land.fisaland.market.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.repository.GatheringRecordInfoRepository;
import com.fisa.land.fisaland.market.repository.MarketRepository;

public class GatheringRecordServiceImpl implements GatheringRecordService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MarketRepository marketRepository;
	
	@Autowired
	GatheringRecordInfoRepository gatheringRecordInfoRepository;

    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	public Long saveGatheringRecord(GatheringRecordInfoDTO.setGatheringRecord gatheringRecord) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(gatheringRecord.getUserId()).orElseThrow();
		Market market = marketRepository.findById(gatheringRecord.getMarketId()).orElseThrow();
		GatheringRecordInfoDTO.saveGatheringRecord saveGatheringRecord = GatheringRecordInfoDTO.saveGatheringRecord.builder()
				.marketId(market)
				.userId(user)
				.meetingTime(gatheringRecord.getMeetingTime())
				.status(gatheringRecord.getStatus())
				.title(gatheringRecord.getTitle())
				.build();
		GatheringRecordInfo gatheringRecordInfo = modelMapper.map(saveGatheringRecord, GatheringRecordInfo.class);
		return gatheringRecordInfoRepository.save(gatheringRecordInfo).getGetherRecordId();
		
	}

}
