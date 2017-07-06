package com.hashmapper.repository;

import org.springframework.data.repository.CrudRepository;

import com.hashmapper.model.ServiceBinding;


public interface ServiceBindingRepository extends CrudRepository<ServiceBinding,String> {
}
