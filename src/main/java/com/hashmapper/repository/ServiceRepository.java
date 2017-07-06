package com.hashmapper.repository;

import org.springframework.data.repository.CrudRepository;

import com.hashmapper.model.Service;

public interface ServiceRepository extends CrudRepository<Service, String> {
}
