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


@RestController
@RequestMapping
public class MarketController {

	@Autowired
	MarketService marketService;
	
	@PostMapping("")
	public Long saveMarket(@RequestBody MarketDTO.setMarket setMarket) {
		return marketService.saveMarket(setMarket);
	}
	
	@GetMapping("markets")
	public List<MarketDTO.getMarket> getMarkets(){
		return marketService.getMarketList();
	}
	
	@GetMapping("/{marketId}")
	public MarketDTO.getMarket getMarket(@PathVariable("marketId") Long marketId){
		return marketService.getMarket(marketId);
	}
}
