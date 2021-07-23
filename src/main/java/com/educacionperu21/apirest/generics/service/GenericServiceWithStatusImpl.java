package com.educacionperu21.apirest.generics.service;

import com.educacionperu21.apirest.entities.GenericEntityAbstractStatus;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericServiceWithStatusImpl<E extends GenericEntityAbstractStatus<Key>, R extends PagingAndSortingRepository<E, Key>, Key> extends GenericServiceImpl<E, R, Key>
    implements GenericServiceWithStatus<E,Key>{


    @Override
    @Transactional
    public boolean ChangeStatus(E e, Estado estado) {
        Optional<E> OptionalentityBD = super.findById(e.getId());
        if(OptionalentityBD.isEmpty()){
            throw new NotFoundException(e.getClass().getName() +  " no existe.");
        }
        E entityBD = OptionalentityBD.get();
        entityBD.setEstado(estado);
        dao.save(entityBD);
        return true;
    }
}
