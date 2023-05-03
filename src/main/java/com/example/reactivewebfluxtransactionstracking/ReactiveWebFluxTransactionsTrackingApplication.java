package com.example.reactivewebfluxtransactionstracking;

import com.example.reactivewebfluxtransactionstracking.dao.CompanyRepository;
import com.example.reactivewebfluxtransactionstracking.dao.TransactionRepository;
import com.example.reactivewebfluxtransactionstracking.model.Company;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveWebFluxTransactionsTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebFluxTransactionsTrackingApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompanyRepository companyRepository, TransactionRepository transactionRepository) {
		return args -> {

			Stream.of("ATB", "COMAR", "UIB", "BTK", "Lloyd").forEach(
					company -> {
						companyRepository.save(new Company(
								null,
								company,
								100+Math.random()*900
						)).subscribe(System.out::println);
					}
			);


		};
	}

}
