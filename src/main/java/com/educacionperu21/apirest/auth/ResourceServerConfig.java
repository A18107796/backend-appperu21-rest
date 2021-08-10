package com.educacionperu21.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.educacionperu21.apirest.enums.Roles;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers(HttpMethod.GET,
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/swagger-ui/**",
						"/v3/api-docs/",
						"/swagger-ui-custom.html",
						"/webjars/**")
				.permitAll()
		/*.antMatchers(HttpMethod.GET, "")
				.permitAll()
				 Especializaciones */
				.antMatchers(HttpMethod.GET,	"/peru21/api/especializaciones/**").hasAnyRole(Roles.ADMIN.toString(), Roles.EMPLEADO.toString(), Roles.INFORMES.toString())
				.antMatchers(HttpMethod.POST, 	"/peru21/api/especializaciones/**").hasAnyRole(Roles.ADMIN.toString(), Roles.CORDINACIONACADEMICA.toString())
				.antMatchers(HttpMethod.PUT, 	"/peru21/api/especializaciones/**").hasAnyRole(Roles.ADMIN.toString(), Roles.CORDINACIONACADEMICA.toString())
				.antMatchers(HttpMethod.DELETE, "/peru21/api/especializaciones/**").hasAnyRole(Roles.ADMIN.toString())
				/* Cursos */
				.antMatchers(HttpMethod.GET,	"/peru21/api/cursos/**").hasAnyRole(Roles.ADMIN.toString(), Roles.EMPLEADO.toString(), Roles.INFORMES.toString())
				.antMatchers(HttpMethod.POST, 	"/peru21/api/cursos/**").hasAnyRole(Roles.ADMIN.toString(), Roles.CORDINACIONACADEMICA.toString())
				.antMatchers(HttpMethod.PUT, 	"/peru21/api/cursos/**").hasAnyRole(Roles.ADMIN.toString(), Roles.CORDINACIONACADEMICA.toString())
				.antMatchers(HttpMethod.DELETE, "/peru21/api/cursos/**").hasAnyRole(Roles.ADMIN.toString())
				/* Empleadsos */
				.antMatchers(HttpMethod.GET, 	"/peru21/api/empleados/**").hasAnyRole(Roles.ADMIN.toString(), Roles.SECRETARIA.toString(),Roles.CORDINACIONACADEMICA.toString())
				.antMatchers(HttpMethod.POST, 	"/peru21/api/empleados/**").hasAnyRole(Roles.ADMIN.toString(), Roles.CORDINACIONACADEMICA.toString(), Roles.SECRETARIA.toString())
				.antMatchers(HttpMethod.PUT, 	"/peru21/api/empleados/**").hasAnyRole(Roles.ADMIN.toString(), Roles.CORDINACIONACADEMICA.toString(), Roles.SECRETARIA.toString())
				.antMatchers(HttpMethod.DELETE, "/peru21/api/empleados/**").hasAnyRole("ADMIN")
				
				.anyRequest().authenticated().and().cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		//configuration.addAllowedOriginPattern("http://localhost:4200");
		configuration.addAllowedOriginPattern("https://peru21appv2.web.app");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		System.out.println("CONFIGURDO");
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
