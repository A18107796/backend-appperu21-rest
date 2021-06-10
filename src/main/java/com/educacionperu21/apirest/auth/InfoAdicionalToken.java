package com.educacionperu21.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Usuario usuario = usuarioService.findByEmail(authentication.getName());
		System.out.println(usuario.getEmpleado());
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("email", usuario.getEmail());
		info.put("nombres", usuario.getEmpleado().getNombres() + ' ' + usuario.getEmpleado().getApellidos());
		info.put("id_usuario", usuario.getIdUsuario());
		info.put("id_empleado", usuario.getEmpleado().getId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
