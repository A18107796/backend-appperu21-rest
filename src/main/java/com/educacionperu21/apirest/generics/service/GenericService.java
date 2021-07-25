package com.educacionperu21.apirest.generics.service;


import java.util.Optional;

import com.educacionperu21.apirest.entities.GenericEntityAbstract;
import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GenericService<T extends GenericEntityAbstract<Key>, Key> {
	
	public Iterable<T> findAll();
	
	public Page<T> findAll(Pageable pageable);
	
	public Optional<T> findById(Key id);
	
	public T save(T entity);
	
	public void deleteById(Key id);
	
	
	public boolean ExistsEntity(Key id);


}
