package com.courier.tracking.model.dto.courier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode (of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourierDTO {

	private String id;
	@NotNull(message = "Cannot be blank")
	private String personalNumber;
	@NotNull(message = "Cannot be blank")
	private String code;
	@NotNull(message = "Cannot be blank")
	private Double lat;
	@NotNull(message = "Cannot be blank")
	private Double lng;
	private Double totalDistance;

}
