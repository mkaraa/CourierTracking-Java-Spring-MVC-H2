package com.courier.tracking.data.courier.repository;

import com.courier.tracking.data.courier.entity.Courier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository ("courierRepository")
public interface CourierRepository extends CrudRepository<Courier, String> {

	boolean existsCourierByCodeOrAndPersonalNumber(String code, String personalNumber);

	Optional<Courier> findCourierByCode(String code);
}
