package com.courier.tracking.service.courier;

import com.courier.tracking.model.dto.courier.CourierDTO;
import com.courier.tracking.model.dto.courier.CourierPositionUpdateRequestDTO;

public interface CourierService {

	CourierDTO create(CourierDTO courierDTO);

	CourierDTO changePositionOfCourier(CourierPositionUpdateRequestDTO courierPositionUpdateRequestDTO);

	CourierDTO findByCode(String code);
}
