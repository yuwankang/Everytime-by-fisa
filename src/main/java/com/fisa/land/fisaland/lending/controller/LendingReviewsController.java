package com.fisa.land.fisaland.lending.controller;

import com.fisa.land.fisaland.lending.dto.LendingReviewsDto;
import com.fisa.land.fisaland.lending.service.LendingReviewsService;
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

    @Autowired
    private LendingReviewsService lendingReviewsService;

    @Operation(summary = "리뷰 등록", description = "특정 상품에 리뷰를 등록하는 API")
    @PostMapping("/{productId}/review")
    public ResponseEntity<LendingReviewsDto> createReview(
            @Parameter(description = "상품 ID", example = "1") @PathVariable("productId") Long productId,
            @RequestBody LendingReviewsDto reviewDto) {
        LendingReviewsDto createdReview = lendingReviewsService.createReview(reviewDto);
        return ResponseEntity.ok(createdReview);
    }

    @Operation(summary = "상품별 리뷰 리스트 조회", description = "특정 상품의 모든 리뷰를 조회하는 API")
    @GetMapping("/{productId}/reviews")
    public ResponseEntity<List<LendingReviewsDto>> getReviewsByProductId(
            @Parameter(description = "상품 ID", example = "1") @PathVariable("productId") Long productId) {
        List<LendingReviewsDto> reviews = lendingReviewsService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @Operation(summary = "리뷰 수정", description = "특정 리뷰를 수정하는 API")
    @PutMapping("/{reviewId}")
    public ResponseEntity<LendingReviewsDto> updateReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId,
            @RequestBody LendingReviewsDto reviewDto) {
        LendingReviewsDto updatedReview = lendingReviewsService.updateReview(reviewId, reviewDto);
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "리뷰 삭제", description = "특정 리뷰를 삭제하는 API")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @Parameter(description = "리뷰 ID", example = "1") @PathVariable("reviewId") Long reviewId) {
        lendingReviewsService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "평점 평균 조회", description = "특정 상품의 리뷰 평점 평균을 조회하는 API")
    @GetMapping("/averageRate/{productId}")
    public Double getAverageRate(
            @Parameter(description = "상품 ID", example = "1") @PathVariable("productId") Long productId) {
        return lendingReviewsService.getAverageRateByProductId(productId);
    }
}
