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


@RestController
@RequestMapping("/market")
public class MarketReviewController {

    @Autowired
    private MarketReviewService marketReviewService;

    @GetMapping("/{marketId}/review")
    public List<MarketReviewDTO> getAllReviews() {
        return marketReviewService.getAllReviews();
    }

    @GetMapping("/{marketId}/review/{id}")
    public MarketReviewDTO getReviewById(@PathVariable Long id) {
        return marketReviewService.getReviewById(id);
    }

    @PostMapping("/{marketId}/review")
    public MarketReviewDTO createReview(@RequestBody MarketReviewDTO reviewDTO) {
        return marketReviewService.createReview(reviewDTO);
    }

    @PutMapping("/{marketId}/review/{id}")
    public MarketReviewDTO updateReview(@PathVariable Long id, @RequestBody MarketReviewDTO reviewDTO) {
        return marketReviewService.updateReview(id, reviewDTO);
    }

    @DeleteMapping("/{marketId}/review/{id}")
    public void deleteReview(@PathVariable Long id) {
        marketReviewService.deleteReview(id);
    }
}