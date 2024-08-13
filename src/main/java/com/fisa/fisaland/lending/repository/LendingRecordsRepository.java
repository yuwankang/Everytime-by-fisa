package com.fisa.fisaland.lending.repository;

import com.fisa.fisaland.lending.entity.LendingRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRecordsRepository extends JpaRepository<LendingRecords, Long> {
}
