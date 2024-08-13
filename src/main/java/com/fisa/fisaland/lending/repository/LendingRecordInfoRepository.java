package com.fisa.fisaland.lending.repository;

import com.fisa.fisaland.lending.entity.LendingRecordInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRecordInfoRepository extends JpaRepository<LendingRecordInfo, Long> {
}
