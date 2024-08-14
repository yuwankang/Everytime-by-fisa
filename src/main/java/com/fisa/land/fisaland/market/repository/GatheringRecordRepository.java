package com.fisa.land.fisaland.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.market.entity.GatheringRecord;
import com.fisa.land.fisaland.market.entity.GatheringRecordId;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;

@Repository
public interface GatheringRecordRepository extends JpaRepository<GatheringRecord, GatheringRecordId>{
	@Query("SELECT gr.id.user FROM GatheringRecord gr WHERE gr.id.getheringRecordInfo = :gatheringRecordInfo")
	List<User> findUsersByGatheringRecordInfo(@Param("gatheringRecordInfo") GatheringRecordInfo gatheringRecordInfo);
}