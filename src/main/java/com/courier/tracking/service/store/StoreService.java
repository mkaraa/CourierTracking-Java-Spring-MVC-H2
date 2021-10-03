package com.courier.tracking.service.store;

import com.courier.tracking.model.dto.store.StoreDTO;

import java.util.List;
import java.util.Optional;

public interface StoreService {

	void createAll(List<StoreDTO> storeDTOs);

	long count();

	List<StoreDTO> findAll();

	Optional<StoreDTO> findById(String id);
}
