package com.fisa.land.fisaland.market.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fisa.land.fisaland.common.entity.UserEntity;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.type.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class GatheringRecordInfoDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class setGatheringRecordInfo{
		private Long userId;
		private Long marketId;
		private Status status;
		private String meetingTime;
		private String title;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	@ToString
	public static class saveGatheringRecordInfo{
		private UserEntity userEntityId;
		private Market marketId;
		private Status status;
		private LocalDateTime meetingTime;
		private String title;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	@ToString
	public static class getGatheringRecordInfo implements Serializable{
		private Long gatheringRecordInfoId;
		private Long userId;
		private Long marketId;
		private String userName;
		private String marketName;
		private Status status;
		private LocalDateTime meetingTime;
		private String title;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	@ToString
	public static class getGatheringRecordInfoDetail implements Serializable{
		getGatheringRecordInfo getGatheringRecordInfos;
		List<String> participants;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	@ToString
	public static class updateGatheringRecordInfo implements Serializable{
		private Long userId;
		private String marketName;
		private Status status;
		private String meetingTime;
		private String title;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	@ToString
	public static class getMyGatheringList implements Serializable{
		List<getGatheringRecordInfo> getGatheringRecordInfoBefore;
		List<getGatheringRecordInfo> getGatheringRecordInfoAfter;
	}
}
