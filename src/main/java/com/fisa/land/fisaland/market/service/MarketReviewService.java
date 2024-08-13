package com.fisa.land.fisaland.market.service;

import java.util.List;
import com.fisa.land.fisaland.market.dto.MarketReviewDTO;

public interface MarketReviewService {
    List<MarketReviewDTO> getAllReviews();
    MarketReviewDTO getReviewById(Long id);
    MarketReviewDTO createReview(MarketReviewDTO reviewDTO);
    MarketReviewDTO updateReview(Long id, MarketReviewDTO reviewDTO);
    void deleteReview(Long id);
}
