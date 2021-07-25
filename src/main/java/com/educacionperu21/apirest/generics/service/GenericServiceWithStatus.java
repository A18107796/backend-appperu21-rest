package com.educacionperu21.apirest.generics.service;

import com.educacionperu21.apirest.entities.GenericEntityAbstractStatus;
import com.educacionperu21.apirest.enums.Estado;

public interface GenericServiceWithStatus<T extends GenericEntityAbstractStatus<Key>, Key>  extends GenericService<T,Key>{

    public boolean ChangeStatus(Key id, Estado estado);

    public Iterable<T> findAllByStatus(Estado estado);


}

