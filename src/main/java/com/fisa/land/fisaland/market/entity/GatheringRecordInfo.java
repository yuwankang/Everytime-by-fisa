package com.fisa.land.fisaland.market.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fisa.land.fisaland.common.entity.BaseTimeEntity;
import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.market.type.Status;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class GatheringRecordInfo extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long getherRecordId;
	
	@JoinColumn(name="marketId")
	@ManyToOne(fetch=FetchType.LAZY)
	private Market market;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Status status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false)
	private LocalDateTime meetingTime;
	
	@Column(nullable=false)
	private String title;
}
