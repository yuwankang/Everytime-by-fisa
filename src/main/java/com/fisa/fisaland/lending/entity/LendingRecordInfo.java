package com.fisa.fisaland.lending.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class LendingRecordInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lending_record_id;

    private LocalDateTime borrow_id; // 대여 시작 날짜
    private LocalDateTime return_date; // 반납 예정 날짜
    private LocalDateTime actual_return_date; // 실제 반납 날짜
    private String status; // 대여 상태 (rented, return_requested, return_completed, overdue)
    private Integer fee; // 대여 요금
    private Integer overdue_fee; // 연체료

    public enum LendingStatus {
        RENTED,
        RETURN_REQUESTED,
        RETURN_COMPLETED,
        OVERDUE
    }
}
