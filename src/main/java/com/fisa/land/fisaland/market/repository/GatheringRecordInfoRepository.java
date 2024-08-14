package com.fisa.land.fisaland.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.market.entity.GatheringRecord;

import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.type.Status;

@Repository
public interface GatheringRecordInfoRepository extends JpaRepository<GatheringRecordInfo, Long>{

	 List<GatheringRecordInfo> findAllByStatus(Status status);
	 
	 List<GatheringRecordInfo> findAllByUserUserIdAndStatus(Long userId, Status status);
}