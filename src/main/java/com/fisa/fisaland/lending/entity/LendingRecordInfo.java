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

    private LocalDateTime borrow_id;
    private LocalDateTime return_date;
    private LocalDateTime actual_return_date;
    private String Enum; // Enum으로 변경 가능
    private Integer fee;
    private Integer overdue_fee;

    // Getters and Setters
}
