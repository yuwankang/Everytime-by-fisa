package com.fisa.land.fisaland.market.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class GatheringRecordInfo extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long getherRecordId;
	
	@JoinColumn(name="marketId")
	@ManyToOne(fetch=FetchType.LAZY)
	private Market market;
	
	@Column(nullable=false)
	private Status status;
	
	@Column(nullable=false)
	private LocalDate meetingTime;
	
	@Column(nullable=false)
	private String title;
}
