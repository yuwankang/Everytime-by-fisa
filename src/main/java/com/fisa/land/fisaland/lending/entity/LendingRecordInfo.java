package com.fisa.land.fisaland.lending.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LendingRecordInfo") // 테이블 이름 설정
public class LendingRecordInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lending_record_id")
    private Long lendingRecordId; // 대여 기록 ID (기본 키)

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate; // 대여 날짜

    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate; // 반납 예정 날짜

    @Column(name = "actual_return_date", nullable = false)
    private LocalDateTime actualReturnDate; // 실제 반납 날짜

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LendingStatus status; // 대여 상태 (Enum 사용)

    @Column(name = "fee", nullable = false)
    private Integer fee; // 대여 금액

    @Column(name = "overdue_fee")
    private Integer overdueFee; // 연체료 (NULL 가능)

    @OneToOne(mappedBy = "lendingRecordInfo")
    private LendingRecords lendingRecords; // LendingRecords와의 1:1 관계

    // 대여 상태 Enum 정의
    public enum LendingStatus {
        RENTED,
        RETURN_REQUESTED,
        RETURN_COMPLETED,
        OVERDUE
    }
}