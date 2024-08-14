package com.fisa.land.fisaland.market.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.market.dto.GatheringRecordDTO;
import com.fisa.land.fisaland.market.entity.GatheringRecord;
import com.fisa.land.fisaland.market.entity.GatheringRecordInfo;
import com.fisa.land.fisaland.market.repository.GatheringRecordRepository;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.market.repository.GatheringRecordInfoRepository;

@Service
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
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        
        GatheringRecordInfo gatheringRecordInfo = gatheringRecordInfoRepository.findById(gatheringRecordInfoId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid gathering record info ID"));

        GatheringRecord gatheringRecord = GatheringRecord.builder()
            .user(user)
            .gatheringRecordInfo(gatheringRecordInfo)
            .build();

        GatheringRecord savedRecord = gatheringRecordRepository.save(gatheringRecord);
        return savedRecord.getGatheringRecordId();
    }


    @Override
    public void deleteGatheringRecord(Long gatheringRecordId) {
        gatheringRecordRepository.deleteById(gatheringRecordId);
    }

    @Override
    public List<GatheringRecordDTO> getGatheringRecordsByUserId(Long userId) {
        List<GatheringRecord> gatheringRecords = gatheringRecordRepository.findByUserUserId(userId);
        return gatheringRecords.stream()
            .map(record -> modelMapper.map(record, GatheringRecordDTO.class))
            .collect(Collectors.toList());
    }
}
