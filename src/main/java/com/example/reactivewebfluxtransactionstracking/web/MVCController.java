package com.example.reactivewebfluxtransactionstracking.web;

import com.example.reactivewebfluxtransactionstracking.dao.CompanyRepository;
import com.example.reactivewebfluxtransactionstracking.dao.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {
    private CompanyRepository companyRepository;
    private TransactionRepository transactionRepository;

    public MVCController(CompanyRepository companyRepository, TransactionRepository transactionRepository) {
        this.companyRepository = companyRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("companies", companyRepository.findAll());
        return "index";
    }
}
