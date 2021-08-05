package com.educacionperu21.apirest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public interface GenericEntity<Key> {

    Key getId();

    void setId(Key id);

}
