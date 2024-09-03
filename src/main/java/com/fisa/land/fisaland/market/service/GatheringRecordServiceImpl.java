package com.fisa.land.fisaland.market.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.entity.UserEntity;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.dto.GatheringRecordDTO;
import com.fisa.land.fisaland.market.entity.GatheringRecord;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.repository.GatheringRecordInfoRepository;
import com.fisa.land.fisaland.market.repository.GatheringRecordRepository;
import com.fisa.land.fisaland.market.type.Status;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GatheringRecordServiceImpl implements GatheringRecordService {

    @Autowired
    private GatheringRecordRepository gatheringRecordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GatheringRecordInfoRepository gatheringRecordInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Long joinGathering(Long userId, Long gatheringRecordInfoId) {
    	UserEntity userEntity = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        
        GatheringRecordInfo gatheringRecordInfo = gatheringRecordInfoRepository.findById(gatheringRecordInfoId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid gathering record info ID"));

        GatheringRecord gatheringRecord = GatheringRecord.builder()
        	.userEntity(userEntity)
            .gatheringRecordInfo(gatheringRecordInfo)
            .build();

        GatheringRecord savedRecord = gatheringRecordRepository.save(gatheringRecord);
        return savedRecord.getGatheringRecordId();
    }


    @Override
    public void deleteGatheringRecord(GatheringRecordDTO.JoinRequest joinRequest) {
        gatheringRecordRepository.deleteByUserEntityUserIdAndGatheringRecordInfoGatheringRecordInfoId(joinRequest.getUserId(), joinRequest.getGatheringRecordInfoId());
    }

    @Override
    public List<GatheringRecordDTO> getGatheringRecordsByUserId(Long userId) {
        List<GatheringRecord> gatheringRecords = gatheringRecordRepository.findByUserEntityUserId(userId);
        return gatheringRecords.stream()
            .map(record -> modelMapper.map(record, GatheringRecordDTO.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Long> getUserIdsAndUpdateStatus(Long gatheringRecordInfoId) {
        List<Long> userIds = gatheringRecordRepository.findUserIdsByGatheringRecordInfoId(gatheringRecordInfoId);
        
        GatheringRecordInfo gatheringRecordInfo = gatheringRecordInfoRepository.findById(gatheringRecordInfoId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid gathering record info ID"));
        gatheringRecordInfo.setStatus(Status.AFTER);  

        return userIds;  
    }
}