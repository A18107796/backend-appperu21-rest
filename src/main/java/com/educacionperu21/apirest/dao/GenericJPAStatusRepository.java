package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.IGenericStatusClass;
import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericJPAStatusRepository<E extends IGenericStatusClass<Key>, Key> extends JpaRepository<E, Key> {

    Iterable<E> findByEstado(Estado estado);
}
