package com.fisa.land.fisaland.market.entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GetheringRecord {
	 @EmbeddedId
	 private GetheringRecordId id;
}
