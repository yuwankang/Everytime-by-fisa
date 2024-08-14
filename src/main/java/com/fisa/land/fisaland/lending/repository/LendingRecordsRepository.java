package com.fisa.land.fisaland.lending.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.land.fisaland.lending.entity.LendingRecords;

public interface LendingRecordsRepository extends JpaRepository<LendingRecords, Long> {
}