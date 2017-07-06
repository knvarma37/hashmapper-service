package com.hashmapper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashmapper.component.HashMapperComponent;
import com.hashmapper.model.Credentials;
import com.hashmapper.model.Service;
import com.hashmapper.model.ServiceBinding;
import com.hashmapper.model.ServiceInstance;
import com.hashmapper.repository.ServiceBindingRepository;
import com.hashmapper.repository.ServiceInstanceRepository;
import com.hashmapper.repository.ServiceRepository;

@RestController
public class ServiceBrokerController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ServiceInstanceRepository serviceInstanceRepository;

    @Autowired
    ServiceBindingRepository serviceBindingRepository;

    @Autowired
    HashMapperComponent hashMapperService;

    @Autowired
    Cloud cloud;

    @RequestMapping("/v2/catalog")
    public Map<String, Iterable<Service>> catalog() {
        Map<String, Iterable<Service>> wrapper = new HashMap<>();
        wrapper.put("services", serviceRepository.findAll());
        return wrapper;
    }

    @RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> create(@PathVariable("id") String id, @RequestBody ServiceInstance serviceInstance) {
        serviceInstance.setId(id);

        boolean exists = serviceInstanceRepository.exists(id);

        if (exists) {
            ServiceInstance existing = serviceInstanceRepository.findOne(id);
            if (existing.equals(serviceInstance)) {
                return new ResponseEntity<>("{}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{}", HttpStatus.CONFLICT);
            }
        } else {
            serviceInstanceRepository.save(serviceInstance);
            hashMapperService.create(id);
            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id,
                                         @RequestParam("service_id") String serviceId,
                                         @RequestParam("plan_id") String planId) {
        boolean exists = serviceRepository.exists(id);

        if (exists) {
            serviceRepository.delete(id);
            hashMapperService.delete(id);
            return new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{}", HttpStatus.GONE);
        }
    }
}
