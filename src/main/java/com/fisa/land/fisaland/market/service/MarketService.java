package com.fisa.land.fisaland.market.service;
import com.fisa.land.fisaland.market.dto.MarketDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface MarketService {
	MarketDTO.getMarets getMarketList();
	
	Long saveMarket(MarketDTO.setMarket market);
}
