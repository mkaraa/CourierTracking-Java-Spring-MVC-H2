package com.courier.tracking.model.mapper.courier;

import com.courier.tracking.data.courier.entity.Courier;
import com.courier.tracking.model.dto.courier.CourierDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CourierMapper {

	private CourierMapper() {
		log.info("Cannot initialized");
	}

	public static CourierDTO toDTO(Courier entity) {
		return CourierDTO.builder()
				.id(entity.getId())
				.code(entity.getCode())
				.lat(entity.getLat())
				.lng(entity.getLng())
				.personalNumber(entity.getPersonalNumber())
				.totalDistance(entity.getTotalDistance())
				.build();
	}

	public static Courier toEntity(CourierDTO dto) {
		return Courier.builder().id(dto.getId()).code(dto.getCode()).lat(dto.getLat()).lng(dto.getLng()).personalNumber(dto.getPersonalNumber()).totalDistance(dto.getTotalDistance()).build();
	}

}
