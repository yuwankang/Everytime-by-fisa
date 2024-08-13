package com.fisa.land.fisaland.market.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class MarketDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class getMarket{
		private Long marketId;
		private String name;
		private String location;
		private String description;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Builder
	@ToString
	public static class setMarket{
		private String name;
		private String location;
		private String description;
	}
}
