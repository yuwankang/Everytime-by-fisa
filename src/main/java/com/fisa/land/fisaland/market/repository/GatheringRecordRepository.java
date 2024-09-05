package com.fisa.land.fisaland.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.common.entity.UserEntity;
import com.fisa.land.fisaland.market.entity.GatheringRecord;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;

@Repository
public interface GatheringRecordRepository extends JpaRepository<GatheringRecord, Long> {

	@Query("SELECT gr.userEntity FROM GatheringRecord gr WHERE gr.gatheringRecordInfo = :gatheringRecordInfo")
	List<UserEntity> findUsersByGatheringRecordInfo(@Param("gatheringRecordInfo") GatheringRecordInfo gatheringRecordInfo);

	void deleteByUserEntityUserIdAndGatheringRecordInfoGatheringRecordInfoId(Long userId, Long gatheringRecordInfoId);

	List<GatheringRecord> findByUserEntityUserId(Long userId);
	
	@Query("SELECT gr.userEntity.userId FROM GatheringRecord gr WHERE gr.gatheringRecordInfo.gatheringRecordInfoId = :gatheringRecordInfoId")
    List<Long> findUserIdsByGatheringRecordInfoId(@Param("gatheringRecordInfoId") Long gatheringRecordInfoId);
}