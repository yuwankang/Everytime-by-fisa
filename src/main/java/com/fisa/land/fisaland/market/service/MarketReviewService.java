package com.fisa.land.fisaland.market.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.market.dto.MarketReviewDTO;
import com.fisa.land.fisaland.market.entity.MarketReview;
import com.fisa.land.fisaland.market.repository.MarketReviewRepository;

@Service
public class MarketReviewService {

    @Autowired
    private MarketReviewRepository marketReviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MarketReviewDTO> getAllReviews() {
        return marketReviewRepository.findAll().stream()
                .map(review -> modelMapper.map(review, MarketReviewDTO.class))
                .collect(Collectors.toList());
    }

    public MarketReviewDTO getReviewById(Long id) {
        MarketReview review = marketReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return modelMapper.map(review, MarketReviewDTO.class);
    }

    public MarketReviewDTO createReview(MarketReviewDTO reviewDTO) {
        MarketReview review = modelMapper.map(reviewDTO, MarketReview.class);
        MarketReview savedReview = marketReviewRepository.save(review);
        return modelMapper.map(savedReview, MarketReviewDTO.class);
    }

    public MarketReviewDTO updateReview(Long id, MarketReviewDTO reviewDTO) {
        MarketReview existingReview = marketReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existingReview.setContent(reviewDTO.getContent());
        existingReview.setRate(reviewDTO.getRate());
        existingReview.setUpdatedAt(LocalDate.now());

        MarketReview updatedReview = marketReviewRepository.save(existingReview);
        return modelMapper.map(updatedReview, MarketReviewDTO.class);
    }

    public void deleteReview(Long id) {
        marketReviewRepository.deleteById(id);
    }
}

