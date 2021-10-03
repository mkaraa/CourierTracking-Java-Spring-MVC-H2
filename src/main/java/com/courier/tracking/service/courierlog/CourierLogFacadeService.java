package com.courier.tracking.service.courierlog;

import com.courier.tracking.model.dto.courierlog.CourierLogDTO;

import java.util.List;

public interface CourierLogFacadeService {

	void fillCourierLogDetailInformation(List<CourierLogDTO> courierLogDTOs);
}
