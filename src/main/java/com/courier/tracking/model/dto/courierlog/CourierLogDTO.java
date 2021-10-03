package com.courier.tracking.model.dto.courierlog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode (of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude (JsonInclude.Include.NON_NULL)
public class CourierLogDTO {

	private String id;

	@NotNull (message = "Cannot be blank")
	private String courierId;
	@NotNull (message = "Cannot be blank")
	private String enteranceStoreId;
	private LocalDateTime lastMovingTime;
	private String storeName;

}
