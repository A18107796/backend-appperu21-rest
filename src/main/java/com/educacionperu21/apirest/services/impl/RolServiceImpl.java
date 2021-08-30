package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.RolDAO;
import com.educacionperu21.apirest.entities.Rol;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.RolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol,RolDAO,Long> implements RolService {
}
