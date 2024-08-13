package com.fisa.land.fisaland.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.market.entity.GetheringRecord;
import com.fisa.land.fisaland.market.entity.GetheringRecordId;

@Repository
public interface GetheringRecordRepository extends JpaRepository<GetheringRecord, GetheringRecordId>{
}
