package com.fisa.land.fisaland.lending.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;

public interface LendingRecordInfoRepository extends JpaRepository<LendingRecordInfo, Long> {
}
