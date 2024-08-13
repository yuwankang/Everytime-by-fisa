package com.fisa.land.fisaland.lending.service;

import com.fisa.land.fisaland.lending.dto.LendingReviewsDto;
import com.fisa.land.fisaland.lending.entity.LendingReviews;
import com.fisa.land.fisaland.lending.repository.LendingReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LendingReviewsServiceImpl implements LendingReviewsService {

    @Autowired
    private LendingReviewsRepository lendingReviewsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LendingReviewsDto> getAllReviews() {
        List<LendingReviews> reviews = lendingReviewsRepository.findAll();
        return reviews.stream()
                      .map(review -> modelMapper.map(review, LendingReviewsDto.class))
                      .collect(Collectors.toList());
    }

    @Override
    public LendingReviewsDto getReviewById(Long reviewId) {
        Optional<LendingReviews> reviewOptional = lendingReviewsRepository.findById(reviewId);
        return reviewOptional.map(review -> modelMapper.map(review, LendingReviewsDto.class)).orElse(null);
    }

    @Override
    public List<LendingReviewsDto> getReviewsByProductId(Long productId) {
        List<LendingReviews> reviews = lendingReviewsRepository.findByProduct_ProductId(productId);
        return reviews.stream()
                      .map(review -> modelMapper.map(review, LendingReviewsDto.class))
                      .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public LendingReviewsDto createReview(LendingReviewsDto reviewDto) {
        LendingReviews review = modelMapper.map(reviewDto, LendingReviews.class);
        LendingReviews savedReview = lendingReviewsRepository.save(review);
        return modelMapper.map(savedReview, LendingReviewsDto.class);
    }

    @Transactional
    @Override
    public LendingReviewsDto updateReview(Long reviewId, LendingReviewsDto reviewDto) {
        Optional<LendingReviews> optionalReview = lendingReviewsRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            LendingReviews review = optionalReview.get();
            modelMapper.map(reviewDto, review);
            LendingReviews updatedReview = lendingReviewsRepository.save(review);
            return modelMapper.map(updatedReview, LendingReviewsDto.class);
        } else {
            // Handle review not found
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteReview(Long reviewId) {
        lendingReviewsRepository.deleteById(reviewId);
    }
}
