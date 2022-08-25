package com.examen.academit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Sale {
    private Long code;
    private Double total;
    private Integer quantity;
    private Product product;
    private Vendor vendor;

    //Constructores:
    public Sale() {
    }
    public Sale(Long code, Double total, Integer quantity, Product product, Vendor vendor) {
        this.code = code;
        this.total = total;
        this.quantity = quantity;
        this.product = product;
        this.vendor = vendor;
    }

    //Getters y Setters:
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Vendor getVendor() {
        return vendor;
    }
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
