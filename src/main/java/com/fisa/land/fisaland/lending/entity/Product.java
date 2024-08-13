package com.fisa.land.fisaland.lending.entity;

import com.fisa.land.fisaland.common.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Product extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private Long userId;
	private String product_name;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;  // enum 타입으로 선언해야 합니다.
	
	private int price;	
	
	@Enumerated(EnumType.STRING)
	private Category category;  // enum 타입으로 선언해야 합니다.
    
    
    // enum 정의는 클래스 내부에 위치시킵니다.
    public enum Status {
        AVAILABLE,
        RENTED
    } 

    public enum Category {
        CLOTHING,
        BOOKS,
        ACCESSORIES,
        ELECTRONICS,
        TOYS,
        OTHERS
    }
}