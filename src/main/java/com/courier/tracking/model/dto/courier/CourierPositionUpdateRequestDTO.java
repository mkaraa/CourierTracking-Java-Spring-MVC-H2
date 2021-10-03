package com.courier.tracking.model.dto.courier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourierPositionUpdateRequestDTO {

	@NotNull(message = "Cannot be blank")
	private String courierId;
	@NotNull(message = "Cannot be blank")
	private Double lat;
	@NotNull(message = "Cannot be blank")
	private Double lng;

}
