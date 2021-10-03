package com.courier.tracking.data.store.repository;

import com.courier.tracking.data.store.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository ("storeRepository")
public interface StoreRepository extends CrudRepository<Store, String> {

}
