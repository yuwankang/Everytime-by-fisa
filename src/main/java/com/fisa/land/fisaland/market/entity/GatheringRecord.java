package com.fisa.land.fisaland.market.entity;

import com.fisa.land.fisaland.common.entity.BaseTimeEntity;
import com.fisa.land.fisaland.common.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter 
@Builder
@Entity
@Table(name = "gathering_record")
public class GatheringRecord extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gatheringRecordId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "gathering_record_info_id", nullable = false)
    private GatheringRecordInfo gatheringRecordInfo;
}