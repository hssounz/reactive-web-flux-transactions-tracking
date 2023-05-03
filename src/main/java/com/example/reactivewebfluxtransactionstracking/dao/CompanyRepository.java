package com.example.reactivewebfluxtransactionstracking.dao;

import com.example.reactivewebfluxtransactionstracking.model.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String > {
    Mono<Company> findByCompanyName(String companyName);
    Mono<Void> deleteByCompanyName(String companyName);
}
