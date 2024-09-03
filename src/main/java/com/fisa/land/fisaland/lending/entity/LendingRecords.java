package com.fisa.land.fisaland.lending.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fisa.land.fisaland.common.entity.UserEntity;
import com.fisa.land.fisaland.lending.dto.LendingRecordDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LendingRecords") // 테이블 이름 설정
public class LendingRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lending_record_id")
    private Long lendingRecordId; // 대여 기록 ID (기본 키)

    @Column(name = "product_id", nullable = false)
    private Long productId; // FK 필드 추가

    @Column(name = "borrower_id", nullable = false)
    private Long borrowerId; // FK 필드 추가

    @Column(name = "owner_id", nullable = false)
    private Long ownerId; // FK 필드 추가

    @Column(name = "created", nullable = false)
    private LocalDateTime created; // 생성 날짜

    // Product와의 ManyToOne 관계 설정
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product; // Product 엔티티와의 관계

    // 대여자 (Borrower)와의 ManyToOne 관계 설정
    @ManyToOne
    @JoinColumn(name = "borrower_id", insertable = false, updatable = false)
    private UserEntity borrower; // User 엔티티와의 관계 (대여자)

    // 소유자 (Owner)와의 ManyToOne 관계 설정
    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private UserEntity owner; // User 엔티티와의 관계 (소유자)

    // LendingRecordInfo와의 1:1 관계 설정
    @OneToOne(mappedBy = "lendingRecords", cascade = CascadeType.ALL)
    @JsonManagedReference
    private LendingRecordInfo lendingRecordInfo; // LendingRecordInfo와의 1:1 관계
    
    
    // 생성 메서드 추가
    public static LendingRecords createLendingRecords(LendingRecordDto dto) {
        LendingRecords lendingRecords = new LendingRecords();
        lendingRecords.setProductId(dto.getProductId());
        lendingRecords.setBorrowerId(dto.getBorrowerId());
        lendingRecords.setOwnerId(dto.getOwnerId());
        lendingRecords.setCreated(LocalDateTime.now());

        // LendingRecordInfo 엔티티 생성 및 설정
        LendingRecordInfo lendingRecordInfo = LendingRecordInfo.createLendingRecordInfo(dto, lendingRecords);
        lendingRecords.setLendingRecordInfo(lendingRecordInfo);

        return lendingRecords;
    }
}