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
        private Category category; 
    }
    
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class getProduct{
		private long productId;
		private String productName;
		private String description;
		private Status status;  
		private int price;	
		private Category category;  
	    
	}
} 