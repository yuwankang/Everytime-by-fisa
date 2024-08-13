package com.fisa.land.fisaland.market.dto;

import java.time.LocalDateTime;

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
	public static class setGatheringRecord{
		private Long userId;
		private Long marketId;
		private Status status;
		private LocalDateTime meetingTime;
		private String title;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class saveGatheringRecord{
		private User userId;
		private Market marketId;
		private Status status;
		private LocalDateTime meetingTime;
		private String title;
	}
	
}
