package com.example.springsecurityjwtmysql.springsecurityjwtmysql;

import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.Role;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.User;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringsecurityjwtmysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityjwtmysqlApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(0,"ROLE_USER"));
			userService.saveRole(new Role(0,"ROLE_MANAGER"));
			userService.saveRole(new Role(0,"ROLE_ADMIN"));
			userService.saveRole(new Role(0,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(0,"John Trovolta","john","1234",new ArrayList<>()));
			userService.saveUser(new User(0,"Will Smith","will","1234",new ArrayList<>()));
			userService.saveUser(new User(0,"Jim Carey","jim","1234",new ArrayList<>()));
			userService.saveUser(new User(0,"Arnold Schwarzenegger","arnold","1234",new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_USER");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");
		};
	}*/

}
