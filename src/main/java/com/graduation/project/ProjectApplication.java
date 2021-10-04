package com.graduation.project;

import com.graduation.project.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication implements ApplicationRunner {

	private final UserRepository userRepository;

	public ProjectApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		userRepository.save(new User(1, "user", "aa@aa.aa", "password"));
//		userRepository.save(new User(2, "admin", "bb@bb.bb", "password"));


		System.out.println(userRepository.findAll());
	}
}
