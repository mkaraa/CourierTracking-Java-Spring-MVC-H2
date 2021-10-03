package com.courier.tracking.service.courierlog;

import com.courier.tracking.model.dto.courierlog.CourierLogDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CourierLogService {

	void create(CourierLogDTO courierLogDTO);

	Optional<CourierLogDTO> findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals(LocalDateTime date, String courierId, String storeId);

	List<CourierLogDTO> findByCourierId(String courierId);
}
