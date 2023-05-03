package com.example.reactivewebfluxtransactionstracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Data @AllArgsConstructor @NoArgsConstructor
public class Company {
    @Id
    private String id;
    private String companyName;
    private double price;
}
