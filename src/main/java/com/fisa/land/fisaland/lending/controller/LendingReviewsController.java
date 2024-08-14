package com.fisa.land.fisaland.lending.controller;

import com.fisa.land.fisaland.lending.dto.LendingReviewsDto;
import com.fisa.land.fisaland.lending.service.LendingReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class LendingReviewsController {

    @Autowired
    private LendingReviewsService lendingReviewsService;

    //리뷰 등록
    @PostMapping
    public ResponseEntity<LendingReviewsDto> createReview(@RequestBody LendingReviewsDto reviewDto) {
        LendingReviewsDto createdReview = lendingReviewsService.createReview(reviewDto);
        return ResponseEntity.ok(createdReview);
    }

    //상품 리뷰 리스트 조회
    @GetMapping("{productId}/reviews")
    public ResponseEntity<List<LendingReviewsDto>> getReviewsByProductId(@PathVariable("productId") Long productId) {
        List<LendingReviewsDto> reviews = lendingReviewsService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
    
    //리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<LendingReviewsDto> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody LendingReviewsDto reviewDto) {
        LendingReviewsDto updatedReview = lendingReviewsService.updateReview(reviewId, reviewDto);
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long reviewId) {
        lendingReviewsService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
    
}