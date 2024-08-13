package com.fisa.land.fisaland.market.service;
import java.util.List;

import com.fisa.land.fisaland.market.dto.MarketDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MarketService {
	List<MarketDTO.getMarket> getMarketList();
	
	Long saveMarket(MarketDTO.setMarket market);
	
	MarketDTO.getMarket getMarket(Long marketId);
}
