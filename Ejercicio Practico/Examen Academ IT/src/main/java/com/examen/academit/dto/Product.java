package com.examen.academit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Product {
    private Long code;
    private String name;
    private Double price;
    private String category;
//    private List<Sale> sales;

    //Constructores:
    public Product() {
    }
    public Product(Long code, String name, Double price, String category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    //Getters y Setters:
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
