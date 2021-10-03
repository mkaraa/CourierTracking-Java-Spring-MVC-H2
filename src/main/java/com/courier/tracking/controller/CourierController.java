package com.courier.tracking.controller;

import com.courier.tracking.model.dto.courier.CourierDTO;
import com.courier.tracking.model.dto.courier.CourierPositionUpdateRequestDTO;
import com.courier.tracking.model.dto.courierlog.CourierLogDTO;
import com.courier.tracking.service.courier.CourierService;
import com.courier.tracking.service.courierlog.CourierLogFacadeService;
import com.courier.tracking.service.courierlog.CourierLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/courier")
public class CourierController {

	private final CourierService courierService;
	private final CourierLogService courierLogService;
	private final CourierLogFacadeService courierLogFacadeService;

	@PostMapping (value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourierDTO> create(@RequestBody @Valid CourierDTO courierDTO) {
		return new ResponseEntity<>(courierService.create(courierDTO), HttpStatus.OK);
	}

	@PostMapping (value = "/move", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourierDTO> create(@RequestBody @Valid CourierPositionUpdateRequestDTO courierPositionUpdateRequestDTO) {
		return new ResponseEntity<>(courierService.changePositionOfCourier(courierPositionUpdateRequestDTO), HttpStatus.OK);
	}

	@GetMapping (value = "/{id}/courier-log", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CourierLogDTO>> findLogsByCourierId(@PathVariable ("id") String courierId) {
		List<CourierLogDTO> courierLogDTOs = courierLogService.findByCourierId(courierId);
		courierLogFacadeService.fillCourierLogDetailInformation(courierLogDTOs);
		return new ResponseEntity<>(courierLogDTOs, HttpStatus.OK);
	}

	@GetMapping (value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourierDTO> findByCode(@PathVariable ("code") String code) {
		CourierDTO courierDTO = courierService.findByCode(code);
		return new ResponseEntity<>(courierDTO, HttpStatus.OK);
	}

}
