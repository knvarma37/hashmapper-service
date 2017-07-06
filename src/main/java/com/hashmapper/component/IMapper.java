package com.hashmapper.component;

public interface IMapper {
	public void create(String instaceId);
	public void delete(String instaceId);
	public void put(String instaceId, Object key, Object value);
	public Object get(String instaceId, Object key);
	public void delete(String instaceId, Object key);
}
