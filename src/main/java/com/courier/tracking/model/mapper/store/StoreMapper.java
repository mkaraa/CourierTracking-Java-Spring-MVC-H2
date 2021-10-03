package com.courier.tracking.model.mapper.store;

import com.courier.tracking.data.store.entity.Store;
import com.courier.tracking.model.dto.store.StoreDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StoreMapper {

	private StoreMapper() {
		log.info("Cannot initialized");
	}

	public static StoreDTO toDTO(Store entity) {
		return StoreDTO.builder().id(entity.getId()).city(entity.getCity()).lat(entity.getLat()).lng(entity.getLng()).name(entity.getName()).build();
	}

	public static Store toEntity(StoreDTO dto) {
		return Store.builder().id(dto.getId()).city(dto.getCity()).lat(dto.getLat()).lng(dto.getLng()).name(dto.getName()).build();
	}

}
