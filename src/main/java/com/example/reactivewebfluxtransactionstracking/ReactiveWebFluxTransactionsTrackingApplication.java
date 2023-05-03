package com.example.reactivewebfluxtransactionstracking;

import com.example.reactivewebfluxtransactionstracking.dao.CompanyRepository;
import com.example.reactivewebfluxtransactionstracking.dao.TransactionRepository;
import com.example.reactivewebfluxtransactionstracking.model.Company;
import com.example.reactivewebfluxtransactionstracking.model.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveWebFluxTransactionsTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebFluxTransactionsTrackingApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompanyRepository companyRepository, TransactionRepository transactionRepository) {
		return args -> {

			transactionRepository.deleteAll().subscribe(null, null, () -> {

				companyRepository.deleteAll().subscribe(null, null, () -> {

					Stream.of("ATB", "COMAR", "UIB", "BTK", "Lloyd").forEach(
							company -> {
								companyRepository.save(new Company(
										null,
										company,
										100+Math.random()*900
								)).subscribe(
										c -> {
											for (int i = 0; i < 10; i++) {
												Transaction transaction = new Transaction(
														null,
														Instant.now(),
														c.getPrice() + ((Math.random()*12) + 6)/100,
														c
												);
												transactionRepository.save(transaction).subscribe(System.out::println);
											}
										}
								);
							}
					);

				});

			});

		};
	}

}
