package com.fisa.land.fisaland.market.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fisa.land.fisaland.common.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.GatheringRecordInfoDTO;
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

		UserEntity userEntity = userRepository.findById(gatheringRecord.getUserId()).orElseThrow();
		Market market = marketRepository.findById(gatheringRecord.getMarketId()).orElseThrow();

		GatheringRecordInfo gatheringRecordInfo = GatheringRecordInfo.builder()
				.userEntity(userEntity)
				.market(market)
				.title(gatheringRecord.getTitle())
				.meetingTime(LocalDateTime.parse(gatheringRecord.getMeetingTime()))
				.status(gatheringRecord.getStatus())
				.build();

		return gatheringRecordInfoRepository.save(gatheringRecordInfo).getGatheringRecordInfoId();

	}

	@Override
	public List<GatheringRecordInfoDTO.getGatheringRecordInfo> getGatheringRecordInfo() {

		List<GatheringRecordInfo> list = gatheringRecordInfoRepository.findAllByStatus(Status.BEFORE);
		return list.stream().map(m ->{
			return GatheringRecordInfoDTO.getGatheringRecordInfo.builder()
					.gatheringRecordInfoId(m.getGatheringRecordInfoId())
					.marketId(m.getMarket().getMarketId())
					.userId(m.getUserEntity().getUserId())
					.userName(m.getUserEntity().getUsername())
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

		GatheringRecordInfo gatheringRecordInfo = gatheringRecordInfoRepository.findById(gatheringRecordId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 모임입니다"));
		GatheringRecordInfoDTO.getGatheringRecordInfo gatheringRecord = GatheringRecordInfoDTO.getGatheringRecordInfo.builder()
				.gatheringRecordInfoId(gatheringRecordInfo.getGatheringRecordInfoId())
				.marketId(gatheringRecordInfo.getMarket().getMarketId())
				.userName(gatheringRecordInfo.getUserEntity().getUsername())
				.marketName(gatheringRecordInfo.getMarket().getName())
				.status(gatheringRecordInfo.getStatus())
				.meetingTime(gatheringRecordInfo.getMeetingTime())
				.title(gatheringRecordInfo.getTitle())
				.build();

		List<String> participants = gatheringRecordRepository.findUsersByGatheringRecordInfo(gatheringRecordInfo)
				.stream()                           // 스트림으로 변환
				.map(UserEntity::getUsername)             // 각 User 객체에서 username 필드만 추출
				.collect(Collectors.toList());
		return GatheringRecordInfoDTO.getGatheringRecordInfoDetail.builder()
				.getGatheringRecordInfos(gatheringRecord)
				.participants(participants)
				.build();
	}

	@Override
	public Long updateGatheringRecordInfo(Long gatheringRecordInfoId, GatheringRecordInfoDTO.updateGatheringRecordInfo updateGatheringRecordInfo) {
		
		/*this.market = market;
		this.status = status;
		this.meetingTime = meetingTime;
		this.title = title;
		*/
		GatheringRecordInfo gri = gatheringRecordInfoRepository.findById(gatheringRecordInfoId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 모임입니다."));
		Market market = marketRepository.findById(gri.getMarket().getMarketId()).orElseThrow();
		market.setName(updateGatheringRecordInfo.getMarketName());
		marketRepository.save(market);
		gri.setGatheringRecordInfo(
				market,
				updateGatheringRecordInfo.getStatus(),
				LocalDateTime.parse(updateGatheringRecordInfo.getMeetingTime()),
				updateGatheringRecordInfo.getTitle()
		);
		return gatheringRecordInfoRepository.save(gri).getGatheringRecordInfoId();
	}

	@Override
	public Long deleteGatheringRecordInfo(Long gatheringRecordInfoId) {
		// TODO Auto-generated method stub
		gatheringRecordInfoRepository.deleteById(gatheringRecordInfoId);
		return gatheringRecordInfoId;
	}

	@Override
	public GatheringRecordInfoDTO.getMyGatheringList getMyGatheringList(Long userId) {
		List<GatheringRecordInfo> gatheringRecordInfoBefore = gatheringRecordInfoRepository.findAllByUserEntityUserIdAndStatus(userId, Status.BEFORE);
		List<GatheringRecordInfoDTO.getGatheringRecordInfo> gatheringRecordInfoDtoBefore = gatheringRecordInfoBefore.stream().map(m->{
			return GatheringRecordInfoDTO.getGatheringRecordInfo.builder()
					.gatheringRecordInfoId(m.getGatheringRecordInfoId())
					.marketId(m.getMarket().getMarketId())
					.userName(m.getUserEntity().getUsername())
					.marketName(m.getMarket().getName())
					.status(m.getStatus())
					.meetingTime(m.getMeetingTime())
					.title(m.getTitle())
					.build();
		}).toList();

		List<GatheringRecordInfo> gatheringRecordInfoAfter = gatheringRecordInfoRepository.findAllByUserEntityUserIdAndStatus(userId, Status.AFTER);
		List<GatheringRecordInfoDTO.getGatheringRecordInfo> gatheringRecordInfoDtoAfter = gatheringRecordInfoAfter.stream().map(m->{
			return GatheringRecordInfoDTO.getGatheringRecordInfo.builder()
					.gatheringRecordInfoId(m.getGatheringRecordInfoId())
					.marketId(m.getMarket().getMarketId())
					.userName(m.getUserEntity().getUsername())
					.marketName(m.getMarket().getName())
					.status(m.getStatus())
					.meetingTime(m.getMeetingTime())
					.title(m.getTitle())
					.build();
		}).toList();

		return GatheringRecordInfoDTO.getMyGatheringList.builder()
				.getGatheringRecordInfoBefore(gatheringRecordInfoDtoBefore)
				.getGatheringRecordInfoAfter(gatheringRecordInfoDtoAfter)
				.build();
	}

}