package com.hashmapper.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class HashMapperComponent {

	
	@Autowired
	@Qualifier("HashMapperImpl")
	IMapper mapper;

    public void create(String instanceId) {
        mapper.create(instanceId);
    }

    public void delete(String instanceId) {
        mapper.delete(instanceId);
    }

    public void put(String instanceId, Object key, Object value) {
        mapper.put(instanceId, key, value);
    }

    public Object get(String instanceId, Object key) {
        return mapper.get(instanceId, key);
    }

    public void delete(String instanceId, Object key) {
	mapper.delete(instanceId, key);
    }
}
