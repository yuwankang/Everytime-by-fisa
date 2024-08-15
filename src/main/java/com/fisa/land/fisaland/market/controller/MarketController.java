package com.fisa.land.fisaland.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.market.dto.MarketDTO;
import com.fisa.land.fisaland.market.service.MarketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("market")
@Tag(name = "마켓 API", description = "마켓 정보 등록, 조회 API")
public class MarketController {

	@Autowired
	private MarketService marketService;

	@Operation(summary = "마켓 등록", description = "새로운 마켓 정보를 등록하는 API")
	@PostMapping("")
	public Long saveMarket(@RequestBody MarketDTO.setMarket setMarket) {
		return marketService.saveMarket(setMarket);
	}

	@Operation(summary = "모든 마켓 정보 조회", description = "등록된 모든 마켓 정보를 조회하는 API")
	@GetMapping("markets")
	public List<MarketDTO.getMarket> getMarkets(){
		return marketService.getMarketList();
	}

	@Operation(summary = "특정 마켓 정보 조회", description = "ID를 기반으로 특정 마켓의 정보를 조회하는 API")
	@GetMapping("/{marketId}")
	public MarketDTO.getMarket getMarket(
			@Parameter(description = "마켓 ID", example = "1") @PathVariable("marketId") Long marketId){
		return marketService.getMarket(marketId);
	}
}
