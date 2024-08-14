package com.fisa.land.fisaland.lending.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.lending.dto.LendingRecordDto;
import com.fisa.land.fisaland.lending.entity.LendingRecordInfo;
import com.fisa.land.fisaland.lending.entity.LendingRecordInfo.LendingStatus;
import com.fisa.land.fisaland.lending.entity.LendingRecords;
import com.fisa.land.fisaland.lending.entity.Product;
import com.fisa.land.fisaland.lending.repository.LendingRecordRepository;
import com.fisa.land.fisaland.lending.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class LendingRecordServiceImpl implements LendingRecordService{

	@Autowired
    private LendingRecordRepository lendingRecordsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	@Override
	public LendingRecords saveLendingRecord(LendingRecordDto lendingRecordInfoDTO) {
		// LendingRecords 엔티티 생성 및 설정
        LendingRecords lendingRecords = LendingRecords.createLendingRecords(lendingRecordInfoDTO);
        return lendingRecordsRepository.save(lendingRecords);
	}

	@Override
	public List<LendingRecords> getLendingRecordsByBorrower(Long borrowerId) {
		return lendingRecordsRepository.findByBorrowerId(borrowerId);
	}

	@Override
	public List<LendingRecords> getLendingRecordsByOwner(Long ownerId) {
		return lendingRecordsRepository.findByOwnerId(ownerId);
	}

	@Override
    public Integer getTotalOverdueFeesByBorrower(Long borrowerId) {
        List<LendingRecords> lendingRecords = getLendingRecordsByBorrower(borrowerId);
        return lendingRecords.stream()
                .map(LendingRecords::getLendingRecordInfo)
                .filter(info -> info != null && info.getOverdueFee() != null)
                .mapToInt(LendingRecordInfo::getOverdueFee)
                .sum();
    }

	@Transactional
	@Override
    public LendingRecordInfo updateLendingRecordStatus(Long lendingRecordId) {
		// 1. 대여 기록 조회
        Optional<LendingRecords> lendingRecordOptional = lendingRecordsRepository.findById(lendingRecordId);
        if (!lendingRecordOptional.isPresent()) {
            throw new RuntimeException("Lending record not found");
        }

        LendingRecords lendingRecord = lendingRecordOptional.get();
        LendingRecordInfo lendingRecordInfo = lendingRecord.getLendingRecordInfo();

        // 2. 대여 상태를 RETURN_COMPLETED로 설정
        lendingRecordInfo.setStatus(LendingRecordInfo.LendingStatus.RETURN_COMPLETED);

        // 3. 실제 반납 날짜를 현재로 설정
        LocalDateTime now = LocalDateTime.now();
        lendingRecordInfo.setActualReturnDate(now);

        // 4. 반납 예정 날짜와 실제 반납 날짜 비교해서 연체료 계산
        LocalDateTime returnDate = lendingRecordInfo.getReturnDate();
        if (now.isAfter(returnDate)) {
            // 연체 상태로 변경
            lendingRecordInfo.setStatus(LendingRecordInfo.LendingStatus.OVERDUE);

            // 상품 가격 조회
            Long productId = lendingRecord.getProductId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // 연체료 계산
            long overdueDays = ChronoUnit.DAYS.between(returnDate, now);
            int price = product.getPrice();
            int overdueFee = (int) (price * overdueDays * 2);

            lendingRecordInfo.setOverdueFee(overdueFee);
        } else {
            lendingRecordInfo.setOverdueFee(0); // 연체료가 없으면 null로 설정
        }

        // LendingRecords와의 연관관계 업데이트
        lendingRecord.setLendingRecordInfo(lendingRecordInfo);
        lendingRecordsRepository.save(lendingRecord);

        return lendingRecordInfo;
    }

}
