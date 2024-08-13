package com.fisa.land.fisaland.lending.service;

import com.fisa.land.fisaland.lending.dto.LendingReviewsDto;
import java.util.List;

public interface LendingReviewsService {
    List<LendingReviewsDto> getAllReviews(); // 모든 리뷰 조회
    LendingReviewsDto getReviewById(Long reviewId); // 특정 리뷰 조회
    List<LendingReviewsDto> getReviewsByProductId(Long productId); // 특정 상품에 대한 리뷰 조회
    LendingReviewsDto createReview(LendingReviewsDto reviewDto); // 리뷰 생성
    LendingReviewsDto updateReview(Long reviewId, LendingReviewsDto reviewDto); // 리뷰 수정
    void deleteReview(Long reviewId); // 리뷰 삭제
}