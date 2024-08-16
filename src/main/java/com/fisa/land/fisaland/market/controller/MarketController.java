package com.fisa.land.fisaland.market.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fisa.land.fisaland.market.dto.MarketDTO;
import com.fisa.land.fisaland.market.service.MarketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/market")
@Tag(name = "마켓 API", description = "마켓 정보 등록, 조회 API")
public class MarketController {

    private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

    @Autowired
    private MarketService marketService;

    @Operation(summary = "마켓 등록", description = "새로운 마켓 정보를 등록하는 API")
    @PostMapping("")
    public ResponseEntity<Long> saveMarket(@RequestBody MarketDTO.setMarket setMarket) {
        Long marketId = marketService.saveMarket(setMarket);
        logger.info("마켓 등록됨 - ID: {}, 세부 정보: {}, 시간: {}", marketId, setMarket, LocalDateTime.now());
        return ResponseEntity.ok(marketId);
    }

    @Operation(summary = "모든 마켓 정보 조회", description = "등록된 모든 마켓 정보를 조회하는 API")
    @GetMapping("/markets")
    public ResponseEntity<List<MarketDTO.getMarket>> getMarkets() {
        List<MarketDTO.getMarket> markets = marketService.getMarketList();
        logger.info("모든 마켓 정보 조회 - 시간: {}", LocalDateTime.now());
        return ResponseEntity.ok(markets);
    }

    @Operation(summary = "특정 마켓 정보 조회", description = "ID를 기반으로 특정 마켓의 정보를 조회하는 API")
    @GetMapping("/{marketId}")
    public ResponseEntity<MarketDTO.getMarket> getMarket(
            @Parameter(description = "마켓 ID", example = "1") @PathVariable("marketId") Long marketId) {
        MarketDTO.getMarket market = marketService.getMarket(marketId);
        logger.info("특정 마켓 정보 조회 - ID: {}, 시간: {}", marketId, LocalDateTime.now());
        return ResponseEntity.ok(market);
    }
}
