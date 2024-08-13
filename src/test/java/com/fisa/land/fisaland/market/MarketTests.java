package com.fisa.land.fisaland.market;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fisa.land.fisaland.market.dto.MarketDTO;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.service.MarketService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MarketTests {
	
	@Autowired
	MarketService marketService;
	
	@Test
	public void insertMarketTest() {
		MarketDTO.setMarket setMarket = MarketDTO.setMarket
				.builder()
				.name("테스트1")
				.location("상암")
				.description("설명1")
				.build();
		
		Long id = marketService.saveMarket(setMarket);
		log.info(id);
	}
}
