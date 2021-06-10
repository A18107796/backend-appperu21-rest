package com.educacionperu21.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.educacionperu21.apirest.enums.Roles;

@SpringBootApplication
public class BackendAppperu21ApirestApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendAppperu21ApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "1234";

		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
			System.out.println(i + 1);
		}
		System.out.println(Roles.CORDINACIONACADEMICA.toString());

	}

}
