package com.fisa.land.fisaland.market.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.repository.MarketRepository;

public class GatheringRecordServiceImpl implements GetheringRecordService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MarketRepository marketRepository;

	@Override
	public Long saveGetheringRecord(GatheringRecordInfoDTO.setGatheringRecord getheringRecord) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(getheringRecord.getUserId()).orElseThrow();
		Market market = marketRepository.findById(getheringRecord.getMarketId()).orElseThrow();
		
		
		return null;
	}

}
