package com.educacionperu21.apirest.generics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public class GenericServiceImpl<E, R extends PagingAndSortingRepository<E, Key>, Key>
		implements GenericService<E, Key> {

	@Autowired
	protected R repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Key id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E alumno) {
		// TODO Auto-generated method stub
		return repository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Key id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
