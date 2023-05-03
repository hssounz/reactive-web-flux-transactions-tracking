package com.example.reactivewebfluxtransactionstracking.web;

import com.example.reactivewebfluxtransactionstracking.dao.CompanyRepository;
import com.example.reactivewebfluxtransactionstracking.dao.TransactionRepository;
import com.example.reactivewebfluxtransactionstracking.model.Company;
import com.example.reactivewebfluxtransactionstracking.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

@RestController
public class TransactionReactiveController {
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;

    public TransactionReactiveController(TransactionRepository transactionRepository, CompanyRepository companyRepository) {
        this.transactionRepository = transactionRepository;
        this.companyRepository = companyRepository;
    }
    @GetMapping("/transactions")
    public Flux<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }
    @GetMapping("/transactions/{companyName}")
    public Flux<Transaction> getCompanyTransactions(@PathVariable(name = "companyName") String name){
       return companyRepository.findByCompanyName(name).flatMapMany(
                c -> transactionRepository.findByCompany(c)
        );
    }
}
