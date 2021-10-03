package com.courier.tracking.data.courierlog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@EqualsAndHashCode (of = "id")
@Table (name = "courier_log")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourierLog {

	@Id
	@Column (name = "id")
	private String id;

	@Column (name = "courier_id")
	private String courierId;

	@Column (name = "enterance_store_id")
	private String enteranceStoreId;

	@CreatedDate
	@Column (name = "last_moving_time")
	private LocalDateTime lastMovingTime;

}
