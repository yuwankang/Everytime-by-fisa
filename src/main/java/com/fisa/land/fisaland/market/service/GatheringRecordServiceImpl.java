package com.fisa.land.fisaland.market.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.repository.GatheringRecordInfoRepository;
import com.fisa.land.fisaland.market.repository.MarketRepository;

@Service
public class GatheringRecordServiceImpl implements GatheringRecordService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MarketRepository marketRepository;
	
	@Autowired
	GatheringRecordInfoRepository gatheringRecordInfoRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public String formatMeetingTime(LocalDateTime meetingTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return meetingTime.format(formatter);
    }
    
    public LocalDateTime parseMeetingTime(String meetingTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(meetingTimeString, formatter);
    }
    
	@Override
	public Long saveGatheringRecord(GatheringRecordInfoDTO.setGatheringRecord gatheringRecord) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(gatheringRecord.getUserId()).orElseThrow();
		Market market = marketRepository.findById(gatheringRecord.getMarketId()).orElseThrow();
		GatheringRecordInfoDTO.saveGatheringRecord saveGatheringRecord = GatheringRecordInfoDTO.saveGatheringRecord.builder()
				.marketId(market)
				.userId(user)
				.meetingTime(LocalDateTime.parse(gatheringRecord.getMeetingTime()))
				.status(gatheringRecord.getStatus())
				.title(gatheringRecord.getTitle())
				.build();
		GatheringRecordInfo gatheringRecordInfo = modelMapper.map(saveGatheringRecord, GatheringRecordInfo.class);
		return gatheringRecordInfoRepository.save(gatheringRecordInfo).getGetherRecordId();
		
	}

}
