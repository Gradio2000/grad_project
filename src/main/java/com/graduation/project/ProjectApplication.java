package com.graduation.project;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.model.UserRole;
import com.graduation.project.repository.RoleRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.UserRoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication implements ApplicationRunner {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserRoleRepository userRoleRepository;


	public ProjectApplication(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userRepository.save(new User(1, "user", "aa@aa.aa", "password"));
		userRepository.save(new User(2, "admin", "bb@bb.bb", "password"));

		roleRepository.save(new Role(1, "User_Role"));
		roleRepository.save(new Role(2, "Admin_Role"));

		userRoleRepository.save(new UserRole(1, 1, 1));
		userRoleRepository.save(new UserRole(2, 2, 1));
		userRoleRepository.save(new UserRole(3, 2, 2));

		System.out.println(roleRepository.findAll());
		System.out.println(userRepository.findAll());
	}
}
