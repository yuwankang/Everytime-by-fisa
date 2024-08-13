package com.example.rentalsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LendingReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    private String content;
    private Double rate;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // Getters and Setters
}
