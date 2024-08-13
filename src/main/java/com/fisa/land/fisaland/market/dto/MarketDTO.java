package com.fisa.land.fisaland.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MarketDTO {
	private Long marketId;
	private String name;
	private String location;
	private String description;
}
