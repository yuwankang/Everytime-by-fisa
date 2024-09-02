package com.fisa.land.fisaland.lending.entity;

import com.fisa.land.fisaland.common.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")  // 테이블 이름 확인
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "product_name")
    private String productName;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    // Enum 정의는 클래스 내부에 위치시킵니다.
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LendingReviews> reviews;
}
