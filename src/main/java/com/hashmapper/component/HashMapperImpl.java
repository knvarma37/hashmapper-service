package com.hashmapper.component;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service("HashMapperImpl")
public class HashMapperImpl implements IMapper {

	/**
	* Instead of using the direct HashMap, I should have my custom hashmap.
	*/
	private Map<String, Map<Object, Object>> hashMaps = new HashMap<>();
	
	public HashMapperImpl() {
	}

	@Override
	public void create(String instaceId) {
		hashMaps.put(instaceId, new HashMap<Object, Object>());
	}

	@Override
	public void delete(String instaceId) {
		hashMaps.remove(instaceId);
	}

	@Override
	public void put(String instaceId, Object key, Object value) {
		Map<Object, Object> mapInstance = hashMaps.get(instaceId);
                if (mapInstance != null) {
		        mapInstance.put(key, value);
 		}

	}

	@Override
	public Object get(String instaceId, Object key) {
		Map<Object, Object> mapInstance = hashMaps.get(instaceId);
                if (mapInstance == null) {
                    return null;
		}
	        return mapInstance.get(key);
	}

	@Override
	public void delete(String instaceId, Object key) {
		Map<Object, Object> mapInstance = hashMaps.get(instaceId);
	        mapInstance.remove(key);
	}

}
