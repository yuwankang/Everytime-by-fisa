package com.fisa.land.fisaland.lending.dto;

import com.fisa.land.fisaland.lending.entity.Product.Category;
import com.fisa.land.fisaland.lending.entity.Product.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ProductDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @ToString
    @Builder
    public static class CreateProduct {
    	private String description;
        private String productName;
        private Integer price;
        private Category category; // ProductCategory는 열거형(enum)으로 정의해야 합니다
    }
    
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class getProduct{
		private long product_id;
		private String product_name;
		private String description;
		private Status status;  
		private int price;	
		private Category category;  
	    
	}
} 