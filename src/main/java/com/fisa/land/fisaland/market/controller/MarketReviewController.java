package com.fisa.land.fisaland.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.market.dto.MarketReviewDTO;
import com.fisa.land.fisaland.market.service.MarketReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/market")
@Tag(name = "마켓 리뷰 API", description = "마켓 리뷰 등록, 조회, 수정 및 삭제 API")
public class MarketReviewController {

    @Autowired
    private MarketReviewService marketReviewService;

    @Operation(summary = "특정 마켓의 모든 리뷰 조회", description = "특정 마켓의 모든 리뷰를 조회하는 API")
    @GetMapping("/{marketId}/review")
    public List<MarketReviewDTO> getAllReviews(
            @Parameter(description = "마켓 ID", example = "1") @PathVariable("marketId") Long marketId) {
        return marketReviewService.getAllReviews();
    }

    @Operation(summary = "특정 리뷰 조회", description = "리뷰 ID를 기반으로 특정 리뷰를 조회하는 API")
    @GetMapping("/{marketId}/review/{id}")
    public MarketReviewDTO getReviewById(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("id") Long id) {
        return marketReviewService.getReviewById(id);
    }

    @Operation(summary = "리뷰 등록", description = "새로운 리뷰를 등록하는 API")
    @PostMapping("/{marketId}/review")
    public MarketReviewDTO createReview(
            @Parameter(description = "마켓 ID", example = "1") @PathVariable("marketId") Long marketId,
            @RequestBody MarketReviewDTO reviewDTO) {
        return marketReviewService.createReview(reviewDTO);
    }

    @Operation(summary = "리뷰 수정", description = "리뷰 ID를 기반으로 리뷰를 수정하는 API")
    @PutMapping("/{marketId}/review/{id}")
    public MarketReviewDTO updateReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("id") Long id,
            @RequestBody MarketReviewDTO reviewDTO) {
        return marketReviewService.updateReview(id, reviewDTO);
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰 ID를 기반으로 리뷰를 삭제하는 API")
    @DeleteMapping("/{marketId}/review/{id}")
    public void deleteReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("id") Long id) {
        marketReviewService.deleteReview(id);
    }

    @Operation(summary = "특정 유저의 리뷰 조회", description = "특정 유저가 작성한 모든 리뷰를 조회하는 API")
    @GetMapping("/user/{userId}/reviews")
    public List<MarketReviewDTO> getReviewsByUserId(
            @Parameter(description = "유저 ID", example = "1") @PathVariable("userId") Long userId) {
        return marketReviewService.getReviewsByUserId(userId);
    }
}
