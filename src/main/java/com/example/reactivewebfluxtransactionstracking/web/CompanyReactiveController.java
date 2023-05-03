package com.example.reactivewebfluxtransactionstracking.web;

import com.example.reactivewebfluxtransactionstracking.dao.CompanyRepository;
import com.example.reactivewebfluxtransactionstracking.dao.TransactionRepository;
import com.example.reactivewebfluxtransactionstracking.model.Company;
import com.example.reactivewebfluxtransactionstracking.model.Transaction;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CompanyReactiveController {
    private CompanyRepository companyRepository;

    public CompanyReactiveController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/companies")
    public Flux<Company> getCompanies(){
        return companyRepository.findAll();
    }
    @GetMapping("/companies/{name}")
    public Mono<Company> getCompany(@PathVariable String name){
        return companyRepository.findByCompanyName(name);
    }
    @PostMapping("/companies")
    public Mono<Company> saveCompany(@RequestBody Company company){
        return companyRepository.save(company);
    }
    @DeleteMapping("/companies/{companyName}")
    public Mono<Void> deleteCompany(@PathVariable String name){
         return companyRepository.deleteByCompanyName(name);
    }




}
