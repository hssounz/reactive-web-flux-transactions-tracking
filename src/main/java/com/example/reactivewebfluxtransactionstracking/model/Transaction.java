package com.example.reactivewebfluxtransactionstracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document @Data @AllArgsConstructor @NoArgsConstructor
public class Transaction {
    @Id
    private String id;
    private Instant instant;
    private double price;
    @DBRef
    private Company company;
}
