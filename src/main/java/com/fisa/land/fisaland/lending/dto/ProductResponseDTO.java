package com.fisa.land.fisaland.lending.dto;

import com.fisa.land.fisaland.lending.entity.Product.Category;
import com.fisa.land.fisaland.lending.entity.Product.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



public class ProductResponseDTO {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@ToString
	@Builder
	public static class ViewProduct {
		private long product_id;
		
		private String product_name;
		private String description;
		
		private Status status;  // enum 타입으로 선언해야 합니다.
		
		private int price;	
		
		private Category category;  // enum 타입으로 선언해야 합니다.
	    
	    
	}
}
