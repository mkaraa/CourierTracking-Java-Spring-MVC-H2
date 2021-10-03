package com.courier.tracking.service.courierlog.impl;

import com.courier.tracking.config.exception.RestCourierTrackingException;
import com.courier.tracking.model.dto.courierlog.CourierLogDTO;
import com.courier.tracking.model.dto.store.StoreDTO;
import com.courier.tracking.service.courier.CourierService;
import com.courier.tracking.service.courierlog.CourierLogFacadeService;
import com.courier.tracking.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierLogFacadeServiceImpl implements CourierLogFacadeService {

	private final CourierService courierService;
	private final StoreService storeService;

	@Override
	public void fillCourierLogDetailInformation(List<CourierLogDTO> courierLogDTOs) {

		if(CollectionUtils.isEmpty(courierLogDTOs)){
			return;
		}

		Map<String, String> storeMap = new HashMap<>();

		courierLogDTOs.forEach(courierLogDTO -> {
			if(storeMap.containsKey(courierLogDTO.getEnteranceStoreId())){
				courierLogDTO.setStoreName(storeMap.get(courierLogDTO.getEnteranceStoreId()));
			} else {
				Optional<StoreDTO> optionalStoreDTO = storeService.findById(courierLogDTO.getEnteranceStoreId());
				if(optionalStoreDTO.isPresent()){
					courierLogDTO.setStoreName(optionalStoreDTO.get().getName());
					storeMap.put(optionalStoreDTO.get().getId(), optionalStoreDTO.get().getName());
				} else {
					throw new RestCourierTrackingException("Error occured while fetching store with id: " + courierLogDTO.getEnteranceStoreId());
				}
			}
			courierLogDTO.setId(null);
			courierLogDTO.setCourierId(null);
			courierLogDTO.setEnteranceStoreId(null);
		});

	}

}
