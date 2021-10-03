package com.courier.tracking.data.courierlog.repository;

import com.courier.tracking.data.courierlog.entity.CourierLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository ("courierLogRepository")
public interface CourierLogRepository extends CrudRepository<CourierLog, String> {

	Optional<CourierLog> findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndEnteranceStoreIdEquals(LocalDateTime date, String courierId, String storeId);

	List<CourierLog> findCourierLogsByCourierIdOrderByLastMovingTimeDesc(String courierId);

}
