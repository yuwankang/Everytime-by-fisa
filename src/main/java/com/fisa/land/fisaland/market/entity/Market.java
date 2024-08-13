package com.fisa.land.fisaland.market.entity;

import com.fisa.land.fisaland.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "market")
public class Market extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long marketId;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 200, nullable = false)
	private String location;
	
	private String description;
}
