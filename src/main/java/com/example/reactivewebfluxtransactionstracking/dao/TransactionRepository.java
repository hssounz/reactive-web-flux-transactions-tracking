package com.example.reactivewebfluxtransactionstracking.dao;

import com.example.reactivewebfluxtransactionstracking.model.Company;
import com.example.reactivewebfluxtransactionstracking.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String > {
    Flux<Transaction> findByCompany(Company company);
}
