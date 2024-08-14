package com.fisa.land.fisaland.market.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.type.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
		private User userId;
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
}
