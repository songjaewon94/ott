package com.dopamine.ott;

import com.dopamine.ott.user.entity.VerifiedUser;
import com.dopamine.ott.user.repository.VerifiedUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OttApplication {

	public static void main(String[] args) {
		SpringApplication.run(OttApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(VerifiedUserRepository VerifiedUserRepository) {
		return (args) -> {
			VerifiedUserRepository.save(VerifiedUser.builder().name("동탁").regNo("921108-1582816").build());
			VerifiedUserRepository.save(VerifiedUser.builder().name("관우").regNo("681108-1582816").build());
			VerifiedUserRepository.save(VerifiedUser.builder().name("손권").regNo("890601-2455116").build());
			VerifiedUserRepository.save(VerifiedUser.builder().name("유비").regNo("790411-1656116").build());
			VerifiedUserRepository.save(VerifiedUser.builder().name("조조").regNo("810326-2715702").build());
		};
	}
}
