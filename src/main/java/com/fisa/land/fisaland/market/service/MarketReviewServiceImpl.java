package com.fisa.land.fisaland.market.service;

import java.util.List;
import java.util.stream.Collectors;

import com.fisa.land.fisaland.common.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.MarketReviewDTO;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.entity.MarketReview;
import com.fisa.land.fisaland.market.repository.MarketRepository;
import com.fisa.land.fisaland.market.repository.MarketReviewRepository;

@Service
public class MarketReviewServiceImpl implements MarketReviewService {

    @Autowired
    private MarketReviewRepository marketReviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<MarketReviewDTO> getAllReviews() {
        return marketReviewRepository.findAll().stream()
                .map(review -> modelMapper.map(review, MarketReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MarketReviewDTO getReviewById(Long id) {
        MarketReview review = marketReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return modelMapper.map(review, MarketReviewDTO.class);
    }

    @Override
    public MarketReviewDTO createReview(MarketReviewDTO reviewDTO) {
        MarketReview review = new MarketReview();

        // User 설정
        UserEntity userEntity = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        review.setUserEntity(userEntity);

        // Market 설정
        Market market = marketRepository.findById(reviewDTO.getMarketId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid market ID"));
        review.setMarket(market);

        // 나머지 필드 설정
        review.setContent(reviewDTO.getContent());
        review.setRate(reviewDTO.getRate());

        MarketReview savedReview = marketReviewRepository.save(review);
        return modelMapper.map(savedReview, MarketReviewDTO.class);
    }

    @Override
    public MarketReviewDTO updateReview(Long id, MarketReviewDTO reviewDTO) {
        MarketReview existingReview = marketReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existingReview.setContent(reviewDTO.getContent());
        existingReview.setRate(reviewDTO.getRate());

        MarketReview updatedReview = marketReviewRepository.save(existingReview);
        return modelMapper.map(updatedReview, MarketReviewDTO.class);
    }

    @Override
    public void deleteReview(Long id) {
        marketReviewRepository.deleteById(id);
    }


    @Override
    public List<MarketReviewDTO> getReviewsByUserId(Long userId) {
        return marketReviewRepository.findByUserEntityUserId(userId).stream()
                .map(review -> modelMapper.map(review, MarketReviewDTO.class))
                .collect(Collectors.toList());
    }

}