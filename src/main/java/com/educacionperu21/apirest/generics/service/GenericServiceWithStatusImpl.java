package com.educacionperu21.apirest.generics.service;

import com.educacionperu21.apirest.dao.GenericJPAStatusRepository;
import com.educacionperu21.apirest.entities.GenericEntity;
import com.educacionperu21.apirest.entities.IGenericStatusClass;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericServiceWithStatusImpl<E extends IGenericStatusClass<Key>, R extends GenericJPAStatusRepository<E, Key>, Key>
    implements GenericServiceWithStatus<E,Key>{

    @Autowired
    protected R dao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        // TODO Auto-generated method stub
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Key id) {
        // TODO Auto-generated method stub
        return dao.findById(id);
    }

    @Override
    @Transactional
    public E save(E alumno) {
        return dao.save(alumno);
    }

    @Override
    @Transactional
    public void deleteById(Key id) {
        // TODO Auto-generated method stub
        dao.deleteById(id);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public boolean ExistsEntity(Key id) {
        return dao.existsById(id);
    }


    @Override
    @Transactional
    public boolean ChangeStatus(Key id, Estado estado) {
        Optional<E> OptionalentityBD = findById(id);

        if(OptionalentityBD.isEmpty()){
            throw new NotFoundException("Entidad no existe, verifique datos");
        }
        E entityBD = OptionalentityBD.get();
        entityBD.setEstado(estado);
        dao.save(entityBD);
        return true;
    }

    @Override
    public Iterable<E> findAllByStatus(Estado estado) {
       return dao.findByEstado(estado);
    }
}
