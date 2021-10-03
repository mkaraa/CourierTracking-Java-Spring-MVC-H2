package com.courier.tracking.courier;

import com.courier.tracking.config.exception.RestCourierTrackingException;
import com.courier.tracking.data.courier.entity.Courier;
import com.courier.tracking.data.courier.repository.CourierRepository;
import com.courier.tracking.data.store.repository.StoreRepository;
import com.courier.tracking.model.dto.courier.CourierDTO;
import com.courier.tracking.model.dto.courier.CourierPositionUpdateRequestDTO;
import com.courier.tracking.model.mapper.courier.CourierMapper;
import com.courier.tracking.service.courier.impl.CourierServiceImpl;
import com.courier.tracking.service.store.StoreService;
import com.google.common.collect.Lists;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@MockitoSettings (strictness = Strictness.LENIENT)
@ExtendWith (MockitoExtension.class)
public class CourierTrackingTest {

	@Mock
	private CourierRepository courierRepository;
	@Mock
	private StoreService storeService;
	@Mock
	private StoreRepository storeRepository;
	@InjectMocks
	private CourierServiceImpl courierService;

	private Courier courier;

	@BeforeEach
	public void init() {
		courier = Courier.builder().id(UUID.randomUUID().toString()).code(RandomString.make(5)).lat(40.0).lng(20.0).personalNumber(RandomString.make(10)).totalDistance(0.0).build();
	}

	@Test
	@DisplayName ("Courier Creation Test")
	public void create() {
		Mockito.doReturn(false).when(courierRepository).existsCourierByCodeOrAndPersonalNumber(anyString(), anyString());
		Mockito.doReturn(Lists.newArrayList()).when(storeRepository).findAll();
		Mockito.doReturn(courier).when(courierRepository).save(any());
		CourierDTO courierDTO = courierService.create(CourierMapper.toDTO(courier));
		Assert.notNull(courierDTO.getCode(), "Code must not be null");
	}

	@Test
	@DisplayName ("Courier Find By Code Test")
	public void findByCode() {
		Mockito.doReturn(Optional.of(courier)).when(courierRepository).findCourierByCode(anyString());
		CourierDTO courierDTO = courierService.findByCode(courier.getCode());
		Assert.notNull(courierDTO.getCode(), "Code must not be null");
	}

	@Test
	@DisplayName ("Courier Position Changing Test")
	public void changePositionOfCourier() {
		Mockito.doReturn(Optional.of(courier)).when(courierRepository).findById(anyString());
		Mockito.doReturn(courier).when(courierRepository).save(any());
		CourierDTO courierDTO = courierService.changePositionOfCourier(CourierPositionUpdateRequestDTO.builder().courierId(courier.getId()).lat(40.5).lng(29.5).build());
		Assertions.assertNotEquals(courierDTO.getLat(), courier.getLat());
		Assertions.assertNotEquals(courierDTO.getTotalDistance(), courier.getTotalDistance());
	}

	@Test
	@DisplayName ("Courier Position Should Give Error If Id of Courier Not Exists Test")
	public void changePositionOfCourierShouldThrowExceptionIfIdNotExists() {
		Assertions.assertThrows(RestCourierTrackingException.class, () -> {
			courierService.changePositionOfCourier(CourierPositionUpdateRequestDTO.builder().courierId(courier.getId()).lat(40.5).lng(29.5).build());
		});
	}



}
