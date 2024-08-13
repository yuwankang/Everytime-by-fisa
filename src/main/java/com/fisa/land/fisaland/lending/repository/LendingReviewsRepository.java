package com.fisa.land.fisaland.lending.repository;

import com.fisa.land.fisaland.lending.entity.LendingReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingReviewsRepository extends JpaRepository<LendingReviews, Long> {
	List<LendingReviews> findByProduct_ProductId(Long productId);
}