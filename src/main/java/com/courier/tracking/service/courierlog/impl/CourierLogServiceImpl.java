package com.courier.tracking.service.courierlog.impl;

import com.courier.tracking.data.courierlog.entity.CourierLog;
import com.courier.tracking.data.courierlog.repository.CourierLogRepository;
import com.courier.tracking.model.dto.courierlog.CourierLogDTO;
import com.courier.tracking.model.mapper.courierlog.CourierLogMapper;
import com.courier.tracking.service.courierlog.CourierLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierLogServiceImpl implements CourierLogService {

	@Qualifier ("courierLogRepository")
	private final CourierLogRepository courierLogRepository;

	@Override
	public void create(CourierLogDTO courierDTO) {
		courierDTO.setId(UUID.randomUUID().toString());
		CourierLog courierLog = CourierLogMapper.toEntity(courierDTO);
		CourierLogMapper.toDTO(courierLogRepository.save(courierLog));
	}

	@Override
	public Optional<CourierLogDTO> findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals(LocalDateTime date, String courierId, String storeId) {
		return courierLogRepository.findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals(date, courierId, storeId).map(CourierLogMapper::toDTO);
	}

	@Override
	public List<CourierLogDTO> findByCourierId(String courierId) {
		return courierLogRepository.findCourierLogsByCourierIdOrderByLastMovingTimeDesc(courierId).stream().map(CourierLogMapper::toDTO).collect(Collectors.toList());
	}

}
