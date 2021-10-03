package com.courier.tracking.service.courier.impl;

import com.courier.tracking.config.exception.RestCourierTrackingException;
import com.courier.tracking.data.courier.entity.Courier;
import com.courier.tracking.data.courier.repository.CourierRepository;
import com.courier.tracking.model.dto.courier.CourierDTO;
import com.courier.tracking.model.dto.courier.CourierPositionUpdateRequestDTO;
import com.courier.tracking.model.dto.courierlog.CourierLogDTO;
import com.courier.tracking.model.dto.store.StoreDTO;
import com.courier.tracking.model.mapper.courier.CourierMapper;
import com.courier.tracking.service.courier.CourierService;
import com.courier.tracking.service.courierlog.CourierLogService;
import com.courier.tracking.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

	private final int STORE_DISTANCE_TO_LOG = 100;

	@Qualifier ("courierRepository")
	private final CourierRepository courierRepository;

	private final StoreService storeService;
	private final CourierLogService courierLogService;

	@Override
	@Transactional
	public CourierDTO create(CourierDTO courierDTO) {

		boolean alreadyExists = courierRepository.existsCourierByCodeOrAndPersonalNumber(courierDTO.getCode(), courierDTO.getPersonalNumber());
		if(alreadyExists){
			throw new RestCourierTrackingException("There is already same courier exists!");
		}

		courierDTO.setId(UUID.randomUUID().toString());
		if(ObjectUtils.isEmpty(courierDTO.getTotalDistance())){
			courierDTO.setTotalDistance(0.0);
		}
		Courier courier = CourierMapper.toEntity(courierDTO);
		CourierDTO createdCourierDTO = CourierMapper.toDTO(courierRepository.save(courier));
		calculateDistance(createdCourierDTO);
		return createdCourierDTO;
	}

	@Override
	@Transactional
	public CourierDTO changePositionOfCourier(CourierPositionUpdateRequestDTO courierPositionUpdateRequestDTO) {

		Optional<Courier> optionalCourier = courierRepository.findById(courierPositionUpdateRequestDTO.getCourierId());
		if(!optionalCourier.isPresent()){
			throw new RestCourierTrackingException("Courier with id " + courierPositionUpdateRequestDTO.getCourierId() + " not exists");
		}
		Courier courier = optionalCourier.get();
		CourierDTO courierDTO = CourierMapper.toDTO(courier);
		courierDTO.setLat(courierPositionUpdateRequestDTO.getLat());
		courierDTO.setLng(courierPositionUpdateRequestDTO.getLng());
		double newDistance = calculateAndChangeTotalDistance(courierPositionUpdateRequestDTO.getLat(), courierPositionUpdateRequestDTO.getLng(), courier.getLat(), courier.getLng());
		courierDTO.setTotalDistance(courierDTO.getTotalDistance() + newDistance);
		log.info("new total distance for courier: " + courierDTO.getCode() + " is" + courierDTO.getTotalDistance());
		courierRepository.save(CourierMapper.toEntity(courierDTO));
		calculateDistance(courierDTO);
		return courierDTO;
	}

	private double calculateAndChangeTotalDistance(Double lat, Double lng, Double lat1, Double lng1) {
		return SloppyMath.haversinMeters(lat, lng, lat1, lng1);
	}

	private void calculateDistance(CourierDTO courierDTO) {
		List<StoreDTO> allStores = storeService.findAll();
		allStores.stream()
				.filter(storeDTO -> SloppyMath.haversinMeters(courierDTO.getLat(), courierDTO.getLng(), storeDTO.getLat(), storeDTO.getLng()) <= STORE_DISTANCE_TO_LOG)
				.findFirst()
				.ifPresent(storeDTO -> {
					log.info("Courier is near to " + storeDTO.getName());

					Optional<CourierLogDTO> optionalCourierLogDTO = courierLogService.findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals(
							LocalDateTime.now().minusMinutes(1), courierDTO.getId(), storeDTO.getId());

					if(optionalCourierLogDTO.isPresent()){
						log.warn("Courier is already logged for store in 1 minutes");
					} else {
						log.info("courier log created for courier:" + courierDTO.getCode() + ", store:" + storeDTO.getName());
						courierLogService.create(
								CourierLogDTO.builder().id(UUID.randomUUID().toString()).courierId(courierDTO.getId()).enteranceStoreId(storeDTO.getId()).lastMovingTime(LocalDateTime.now()).build());
					}
				});
	}

	@Override
	public CourierDTO findByCode(String code) {
		Optional<Courier> optional = courierRepository.findCourierByCode(code);
		if(!optional.isPresent()){
			throw new RestCourierTrackingException("Cannot find courier with code: " + code, 404);
		}
		return CourierMapper.toDTO(optional.get());
	}
}
