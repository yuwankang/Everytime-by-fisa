package com.fisa.land.fisaland.market.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.market.dto.MarketDTO;
import com.fisa.land.fisaland.market.dto.MarketDTO.setMarket;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.repository.MarketRepository;

import jakarta.transaction.Transactional;

@Service
public class MarketServiceImpl implements MarketService{
	
	@Autowired
	MarketRepository marketRepository;

    @Autowired
    private ModelMapper modelMapper;

	@Override
	public List<MarketDTO.getMarket> getMarketList() {
		List<MarketDTO.getMarket> markets =  marketRepository.findAll().stream()
				.map(market -> modelMapper.map(market, MarketDTO.getMarket.class))
				.collect(Collectors.toList());
		return markets;
	}

	@Override
	public Long saveMarket(MarketDTO.setMarket requestMarket) {
		// TODO Auto-generated method stub
		Market market = Market.builder()
				.name(requestMarket.getName())
				.description(requestMarket.getDescription())
				.location(requestMarket.getLocation())
				.build();
		
		return marketRepository.save(market).getMarketId();
	}

	@Override
	public MarketDTO.getMarket getMarket(Long marketId) {
		// TODO Auto-generated method stub
		Market market = marketRepository.findById(marketId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 market id입니다"));
		return modelMapper.map(market, MarketDTO.getMarket.class);
	}

}
