package com.io.zerei_games_usados_aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.io.zerei_games_usados_aws.repository.game")
@EntityScan(basePackages = "com.io.zerei_games_usados_aws.model")
public class ZereiGamesUsadosAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZereiGamesUsadosAwsApplication.class, args);
	}

}
