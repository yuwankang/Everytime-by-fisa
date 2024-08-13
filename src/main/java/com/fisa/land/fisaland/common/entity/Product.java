package com.fisa.land.fisaland.common.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Product {
	@Id
	private long product_id;
	
	private long user_id;
	private String product_name;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;  // enum 타입으로 선언해야 합니다.
	
	private int price;
	
	@Enumerated(EnumType.STRING)
	private Category category;  // enum 타입으로 선언해야 합니다.
	
	@Column(updatable = false)  // created_at은 생성 시에만 설정되므로 업데이트되지 않도록 설정
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	// enum 정의는 클래스 내부에 위치시킵니다.
	public enum Status {
        AVAILABLE,
        RENTED,
        DELETED
    }

    public enum Category {
        CLOTHING,
        BOOKS,
        ACCESSORIES,
        ELECTRONICS,
        TOYS
    }
}