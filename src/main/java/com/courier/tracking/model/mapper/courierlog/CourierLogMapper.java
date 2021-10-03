package com.courier.tracking.model.mapper.courierlog;

import com.courier.tracking.data.courierlog.entity.CourierLog;
import com.courier.tracking.model.dto.courierlog.CourierLogDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CourierLogMapper {

	private CourierLogMapper() {
		log.info("Cannot initialized");
	}

	public static CourierLogDTO toDTO(CourierLog entity) {
		return CourierLogDTO.builder().id(entity.getId()).courierId(entity.getCourierId()).enteranceStoreId(entity.getEnteranceStoreId()).lastMovingTime(entity.getLastMovingTime()).build();
	}

	public static CourierLog toEntity(CourierLogDTO dto) {
		return CourierLog.builder().id(dto.getId()).courierId(dto.getCourierId()).enteranceStoreId(dto.getEnteranceStoreId()).lastMovingTime(dto.getLastMovingTime()).build();
	}

}
