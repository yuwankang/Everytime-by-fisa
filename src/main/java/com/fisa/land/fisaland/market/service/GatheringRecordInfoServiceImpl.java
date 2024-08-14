package com.fisa.land.fisaland.market.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
import com.fisa.land.fisaland.market.dto.MarketReviewDTO;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.repository.GatheringRecordInfoRepository;
import com.fisa.land.fisaland.market.repository.GatheringRecordRepository;
import com.fisa.land.fisaland.market.repository.MarketRepository;
import com.fisa.land.fisaland.market.type.Status;

@Service
public class GatheringRecordInfoServiceImpl implements GatheringRecordInfoService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MarketRepository marketRepository;
	
	@Autowired
	GatheringRecordInfoRepository gatheringRecordInfoRepository;
	
	@Autowired
	GatheringRecordRepository gatheringRecordRepository;

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
	public Long saveGatheringRecordInfo(GatheringRecordInfoDTO.setGatheringRecordInfo gatheringRecord) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(gatheringRecord.getUserId()).orElseThrow();
		Market market = marketRepository.findById(gatheringRecord.getMarketId()).orElseThrow();
		GatheringRecordInfoDTO.saveGatheringRecordInfo saveGatheringRecord = GatheringRecordInfoDTO.saveGatheringRecordInfo.builder()
				.marketId(market)
				.userId(user)
				.meetingTime(LocalDateTime.parse(gatheringRecord.getMeetingTime()))
				.status(gatheringRecord.getStatus())
				.title(gatheringRecord.getTitle())
				.build();
		GatheringRecordInfo gatheringRecordInfo = modelMapper.map(saveGatheringRecord, GatheringRecordInfo.class);
		return gatheringRecordInfoRepository.save(gatheringRecordInfo).getGatheringRecordInfoId();
		
	}

	/*
	 * 
	 * private String userName;
		private String marketName;
		private Status status;
		private String meetingTime;
		private String title;
	 */
	@Override
	public List<GatheringRecordInfoDTO.getGatheringRecordInfo> getGatheringRecordInfo() {
		// TODO Auto-generated method stub
		List<GatheringRecordInfo> list = gatheringRecordInfoRepository.findAllByStatus(Status.BEFORE);
		return list.stream().map(m ->{
			return GatheringRecordInfoDTO.getGatheringRecordInfo.builder()
				.gatheringRecordInfoId(m.getGatheringRecordInfoId())
				.userName(m.getUser().getUsername())
				.marketName(m.getMarket().getName())
				.title(m.getTitle())
				.status(m.getStatus())
				.meetingTime(m.getMeetingTime())
				.build();
		}).collect(Collectors.toList());
	
	}

	/*
	 * private Long gatheringRecordInfoId;
		private String userName;
		private String marketName;
		private Status status;
		private LocalDateTime meetingTime;
		private String title;
	 */
	@Override
	public GatheringRecordInfoDTO.getGatheringRecordInfoDetail getGatheringRecordInfoDetail(
			Long gatheringRecordId) {
		// TODO Auto-generated method stub
		
		GatheringRecordInfo gatheringRecordInfo = gatheringRecordInfoRepository.findById(gatheringRecordId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 모임입니다"));
		GatheringRecordInfoDTO.getGatheringRecordInfo gatheringRecord = GatheringRecordInfoDTO.getGatheringRecordInfo.builder()
			.gatheringRecordInfoId(gatheringRecordInfo.getGatheringRecordInfoId())
			.userName(gatheringRecordInfo.getUser().getUsername())
			.marketName(gatheringRecordInfo.getMarket().getName())
			.status(gatheringRecordInfo.getStatus())
			.meetingTime(gatheringRecordInfo.getMeetingTime())
			.title(gatheringRecordInfo.getTitle())
			.build();
		
		List<String> participants = gatheringRecordRepository.findUsersByGatheringRecordInfo(gatheringRecordInfo)
			    .stream()                           // 스트림으로 변환
			    .map(User::getUsername)             // 각 User 객체에서 username 필드만 추출
			    .collect(Collectors.toList());  
		return GatheringRecordInfoDTO.getGatheringRecordInfoDetail.builder()
		.getGatheringRecordInfos(gatheringRecord)
		.participants(participants)
		.build();
	}

}
