package com.fisa.land.fisaland.market.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fisa.land.fisaland.market.dto.MarketReviewDTO;
import com.fisa.land.fisaland.market.service.MarketReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/market/review")
@Tag(name = "마켓 리뷰 API", description = "마켓에 대한 리뷰 등록, 조회 API")
public class MarketReviewController {

    private static final Logger logger = LoggerFactory.getLogger(MarketReviewController.class);

    @Autowired
    private MarketReviewService marketReviewService;

    @Operation(summary = "리뷰 등록", description = "특정 마켓에 리뷰를 등록하는 API")
    @PostMapping("")
    public ResponseEntity<Long> saveReview(@RequestBody MarketReviewDTO reviewDTO) {
        MarketReviewDTO createdReview = marketReviewService.createReview(reviewDTO);
        logger.info("Review created with ID {} for market {} by user {} on {}", 
                    createdReview.getReviewId(), createdReview.getMarketId(), createdReview.getUserId(), LocalDate.now());
        return ResponseEntity.ok(createdReview.getReviewId());
    }

    @Operation(summary = "리뷰 조회", description = "특정 마켓의 모든 리뷰를 조회하는 API")
    @GetMapping("/market/{marketId}")
    public ResponseEntity<List<MarketReviewDTO>> getReviewsByMarketId(
            @Parameter(description = "마켓 ID", example = "1") @PathVariable("marketId") Long marketId) {
        List<MarketReviewDTO> reviews = marketReviewService.getAllReviews(); // Adjusted to call getAllReviews()
        logger.info("Fetched reviews for market {} on {}", marketId, LocalDate.now());
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "리뷰 상세 조회", description = "특정 리뷰의 상세 정보를 조회하는 API")
    @GetMapping("/{reviewId}")
    public ResponseEntity<MarketReviewDTO> getReviewDetail(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId) {
        MarketReviewDTO review = marketReviewService.getReviewById(reviewId);
        logger.info("Fetched details for review {} on {}", reviewId, LocalDate.now());
        return ResponseEntity.ok(review);
    }

    @Operation(summary = "리뷰 수정", description = "특정 리뷰를 수정하는 API")
    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId,
            @RequestBody MarketReviewDTO reviewDTO) {
        marketReviewService.updateReview(reviewId, reviewDTO);
        logger.info("Updated review {} on {}", reviewId, LocalDate.now());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "리뷰 삭제", description = "특정 리뷰를 삭제하는 API")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId) {
        marketReviewService.deleteReview(reviewId);
        logger.info("Deleted review {} on {}", reviewId, LocalDate.now());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "특정 유저의 리뷰 조회", description = "특정 유저가 작성한 모든 리뷰를 조회하는 API")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MarketReviewDTO>> getReviewsByUserId(
            @Parameter(description = "유저 ID", example = "1") @PathVariable("userId") Long userId) {
        List<MarketReviewDTO> reviews = marketReviewService.getReviewsByUserId(userId);
        logger.info("Fetched reviews for user {} on {}", userId, LocalDate.now());
        return ResponseEntity.ok(reviews);
    }
}
