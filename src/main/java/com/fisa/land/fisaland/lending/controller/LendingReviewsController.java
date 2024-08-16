package com.fisa.land.fisaland.lending.controller;

import com.fisa.land.fisaland.lending.dto.LendingReviewsDto;
import com.fisa.land.fisaland.lending.service.LendingReviewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "대여 리뷰 API", description = "상품별 대여 리뷰 등록, 조회, 수정 및 삭제 API")
public class LendingReviewsController {

    private static final Logger LOGGER = Logger.getLogger(LendingReviewsController.class);

    @Autowired
    private LendingReviewsService lendingReviewsService;

    @Operation(summary = "리뷰 등록", description = "특정 상품에 리뷰를 등록하는 API")
    @PostMapping("/{productId}/review")
    public ResponseEntity<LendingReviewsDto> createReview(
            @Parameter(description = "상품 ID", example = "1") @PathVariable("productId") Long productId,
            @RequestBody LendingReviewsDto reviewDto) {
        LOGGER.info("리뷰 등록 호출됨: 상품 ID: " + productId + ", 리뷰 정보: " + reviewDto);
        LendingReviewsDto createdReview = lendingReviewsService.createReview(reviewDto);
        // 상품 ID를 사용하여 로그를 기록
        LOGGER.info("리뷰 등록 성공: 상품 ID: " + createdReview.getProductId());
        return ResponseEntity.ok(createdReview);
    }

    @Operation(summary = "상품별 리뷰 리스트 조회", description = "특정 상품의 모든 리뷰를 조회하는 API")
    @GetMapping("/{productId}/reviews")
    public ResponseEntity<List<LendingReviewsDto>> getReviewsByProductId(
            @Parameter(description = "상품 ID", example = "1") @PathVariable("productId") Long productId) {
        LOGGER.info("상품별 리뷰 리스트 조회 호출됨: 상품 ID: " + productId);
        List<LendingReviewsDto> reviews = lendingReviewsService.getReviewsByProductId(productId);
        LOGGER.info("상품별 리뷰 리스트 조회 성공: 총 리뷰 수: " + reviews.size());
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "리뷰 수정", description = "특정 리뷰를 수정하는 API")
    @PutMapping("/{reviewId}")
    public ResponseEntity<LendingReviewsDto> updateReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId,
            @RequestBody LendingReviewsDto reviewDto) {
        LOGGER.info("리뷰 수정 호출됨: 리뷰 ID: " + reviewId + ", 수정 정보: " + reviewDto);
        LendingReviewsDto updatedReview = lendingReviewsService.updateReview(reviewId, reviewDto);
        if (updatedReview != null) {
            LOGGER.info("리뷰 수정 성공: 리뷰 ID: " + reviewId);
            return ResponseEntity.ok(updatedReview);
        } else {
            LOGGER.warn("리뷰 수정 실패: 리뷰 ID: " + reviewId + "를 찾을 수 없습니다.");
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "리뷰 삭제", description = "특정 리뷰를 삭제하는 API")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId) {
        LOGGER.info("리뷰 삭제 호출됨: 리뷰 ID: " + reviewId);
        lendingReviewsService.deleteReview(reviewId);
        LOGGER.info("리뷰 삭제 성공: 리뷰 ID: " + reviewId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "평점 평균 조회", description = "특정 상품의 리뷰 평점 평균을 조회하는 API")
    @GetMapping("/averageRate/{productId}")
    public Double getAverageRate(
            @Parameter(description = "상품 ID", example = "1") @PathVariable("productId") Long productId) {
        LOGGER.info("상품 평점 평균 조회 호출됨: 상품 ID: " + productId);
        Double averageRate = lendingReviewsService.getAverageRateByProductId(productId);
        LOGGER.info("상품 평점 평균 조회 성공: 상품 ID: " + productId + ", 평점 평균: " + averageRate);
        return averageRate;
    }
}
