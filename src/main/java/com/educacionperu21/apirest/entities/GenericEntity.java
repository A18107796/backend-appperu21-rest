package com.educacionperu21.apirest.entities;

public interface GenericEntity<Key> {

    Key getId();

    void setId(Key id);

}
