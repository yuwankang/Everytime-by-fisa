package com.fisa.land.fisaland.market.entity;

import java.io.Serializable;

import com.fisa.land.fisaland.common.entity.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetheringRecordId implements Serializable {
	@ManyToOne
    private User user;

    @ManyToOne
    private GatheringRecordInfo getheringRecordInfo;
}
