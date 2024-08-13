package com.fisa.land.fisaland.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.market.entity.GatheringRecord;
import com.fisa.land.fisaland.market.entity.GatheringRecordId;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;

@Repository
public interface GatheringRecordInfoRepository extends JpaRepository<GatheringRecordInfo, Long>{

}
