package com.examen.academit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Vendor {
    private Long code;
    private String name;
    private Double salary;

    //Constructor:
    public Vendor() {
    }
    public Vendor(Long code, String name, Double salary) {
        this.code = code;
        this.name = name;
        this.salary = salary;
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
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
