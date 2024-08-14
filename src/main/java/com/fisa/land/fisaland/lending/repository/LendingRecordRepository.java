package com.fisa.land.fisaland.lending.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.land.fisaland.lending.entity.LendingRecordInfo.LendingStatus;
import com.fisa.land.fisaland.lending.entity.LendingRecords;

@Repository
public interface LendingRecordRepository extends JpaRepository<LendingRecords, Long> {
	
    List<LendingRecords> findByBorrowerId(Long borrowerId);
    
    List<LendingRecords> findByOwnerId(Long ownerId);
    
    @Query("SELECT lr FROM LendingRecords lr JOIN lr.lendingRecordInfo lri WHERE lr.productId = :productId AND lri.status IN :statuses")
    Optional<LendingRecords> findByProductIdAndStatusIn(
            @Param("productId") Long productId,
            @Param("statuses") List<LendingRecordInfo.LendingStatus> statuses
    );
}