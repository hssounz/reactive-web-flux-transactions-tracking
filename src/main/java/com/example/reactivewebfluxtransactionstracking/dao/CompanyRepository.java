package com.example.reactivewebfluxtransactionstracking.dao;

import com.example.reactivewebfluxtransactionstracking.model.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String > {
}
