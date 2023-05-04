package com.example.reactivewebfluxtransactionstracking.web;

import com.example.reactivewebfluxtransactionstracking.dao.CompanyRepository;
import com.example.reactivewebfluxtransactionstracking.dao.TransactionRepository;
import com.example.reactivewebfluxtransactionstracking.model.Company;
import com.example.reactivewebfluxtransactionstracking.model.Transaction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Locale;
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
    @PostMapping("/transactions")
    public Mono<Transaction> saveTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }
    @DeleteMapping("/transactions/{id}")
    public Mono<Void> deleteTransaction(@PathVariable String id){
        return transactionRepository.deleteById(id);
    }
    @GetMapping(value = "/transactions/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactions(){
        Flux<Long> interval = Flux.interval(Duration.ofMillis(600));
        return Flux.zip(interval, transactionRepository.findAll()).map(Tuple2::getT2);
    }

    @GetMapping(value = "/transactions/stream/{companyName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactionsByCompany(@PathVariable String companyName){
        Flux<Long> interval = Flux.interval(Duration.ofMillis(600));
        return companyRepository.findByCompanyName(companyName).flatMapMany(
                c -> Flux.zip(interval, transactionRepository.findByCompany(c)).map(Tuple2::getT2)
        );
    }
}
