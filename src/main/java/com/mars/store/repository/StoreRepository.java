package com.mars.store.repository;

import com.mars.store.domain.entity.Store;

public interface StoreRepository {

  Store findByName(String name);
}
