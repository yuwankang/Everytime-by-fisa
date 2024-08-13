package com.fisa.land.fisaland.lending.dto;

import com.fisa.land.fisaland.lending.entity.Product.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProductDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    @Builder
    public static class CreateProduct {
        private String productName;
        private Integer price;
        private Category category; // ProductCategory는 열거형(enum)으로 정의해야 합니다
    }
} 