package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.GenericEntityAbstractStatus;
import com.educacionperu21.apirest.entities.IGenericStatusClass;
import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface GenericJPAStatusRepository<E extends GenericEntityAbstractStatus<Key>, Key> extends JpaRepository<E, Key> {

    Iterable<E> findByEstado(Estado estado);
}
