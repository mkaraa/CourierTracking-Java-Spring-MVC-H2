package com.courier.tracking.service.store.impl;

import com.courier.tracking.config.exception.RestCourierTrackingException;
import com.courier.tracking.data.store.entity.Store;
import com.courier.tracking.data.store.repository.StoreRepository;
import com.courier.tracking.model.dto.store.StoreDTO;
import com.courier.tracking.model.mapper.store.StoreMapper;
import com.courier.tracking.service.store.StoreService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	@Qualifier ("storeRepository")
	private final StoreRepository storeRepository;

	@Override
	public void createAll(List<StoreDTO> storeDTOs) {
		if(CollectionUtils.isEmpty(storeDTOs)){
			throw new RestCourierTrackingException("storeDtos cannot be empty");
		}
		List<Store> stores = storeDTOs.stream().map(StoreMapper::toEntity).collect(Collectors.toList());
		storeRepository.saveAll(stores);
	}

	@Override
	public long count() {
		return storeRepository.count();
	}

	@Override
	public List<StoreDTO> findAll() {
		Iterable<Store> allStoresIterable = storeRepository.findAll();
		List<Store> allStores = Lists.newArrayList(allStoresIterable);
		if(CollectionUtils.isEmpty(allStores)){
			return Lists.newArrayList();
		}
		return allStores.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<StoreDTO> findById(String id) {
		return storeRepository.findById(id).map(StoreMapper::toDTO);
	}
}
