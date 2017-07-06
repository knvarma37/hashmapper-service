package com.hashmapper.repository;

import org.springframework.data.repository.CrudRepository;

import com.hashmapper.model.ServiceInstance;

public interface ServiceInstanceRepository extends CrudRepository<ServiceInstance, String> {
}
