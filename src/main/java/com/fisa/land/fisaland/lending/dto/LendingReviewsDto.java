package com.fisa.land.fisaland.lending.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LendingReviewsDto {

    private String content; // 리뷰 내용

    private Double rate; // 평점

    private LocalDateTime createdAt; // 생성 날짜

    private LocalDateTime updatedAt; // 마지막 수정 날짜

    private Long productId; // 상품 ID (product_id)
    
    private Long userId; // 사용자 ID (user_id)
}
