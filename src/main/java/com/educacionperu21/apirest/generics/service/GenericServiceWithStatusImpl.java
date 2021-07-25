package com.educacionperu21.apirest.generics.service;

import com.educacionperu21.apirest.dao.GenericJPAStatusRepository;
import com.educacionperu21.apirest.entities.GenericEntityAbstractStatus;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericServiceWithStatusImpl<E extends GenericEntityAbstractStatus<Key>, R extends GenericJPAStatusRepository<E, Key>, Key> extends GenericServiceImpl<E, R, Key>
    implements GenericServiceWithStatus<E,Key>{


    @Override
    @Transactional
    public boolean ChangeStatus(Key id, Estado estado) {
        Optional<E> OptionalentityBD = super.findById(id);

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
