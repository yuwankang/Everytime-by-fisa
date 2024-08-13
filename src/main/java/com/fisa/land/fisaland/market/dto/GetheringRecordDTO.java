package com.fisa.land.fisaland.market.dto;

import java.time.LocalDateTime;

import com.fisa.land.fisaland.market.type.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class GetheringRecordDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class setGetheringRecord{
		private Long userId;
		private Long marketId;
		private Status status;
		private LocalDateTime meetingTime;
		private String title;
	}
}
