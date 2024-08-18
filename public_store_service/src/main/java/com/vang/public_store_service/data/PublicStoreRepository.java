package com.vang.public_store_service.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublicStoreRepository extends MongoRepository<PublicStores, Integer> {
}