package com.fisa.land.fisaland.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.entity.Market;
import com.fisa.land.fisaland.market.repository.GatheringRecordRepository;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.repository.MarketRepository;

@Service
public class GatheringRecordServiceImpl2 implements GatheringRecordService2 {

    @Autowired
    private GatheringRecordRepository gatheringRecordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MarketRepository marketRepository;

    @Override
    public Long joinGathering(Long userId, Long gatherRecordId) {
        // User와 Market 객체를 데이터베이스에서 조회
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        
        Market market = marketRepository.findById(gatherRecordId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid gathering ID"));

        // 새로운 GatheringRecordInfo 객체 생성 및 설정
        GatheringRecordInfo record = GatheringRecordInfo.builder()
            .user(user)
            .market(market)
            .build();

        // 객체 저장 후 ID 반환
        GatheringRecordInfo savedRecord = gatheringRecordRepository.save(record);
        return savedRecord.getGetherRecordId(); //변수명 오타 수정 필요
    }
}
