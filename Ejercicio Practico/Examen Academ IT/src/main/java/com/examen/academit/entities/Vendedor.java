package com.examen.academit.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "vendedores")
public class Vendedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long idVendedor;
    @Column(name = "codigo_vendedor", unique = true, nullable = false)
    private Long codigoVendedor;
    private String nombre;
    private Double sueldo;

    //Constructores:
    public Vendedor() {
    }
    public Vendedor(Long idVendedor, Long codigoVendedor, String nombre, Double sueldo) {
        this.idVendedor = idVendedor;
        this.codigoVendedor = codigoVendedor;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    //Getters y Setters:
    public Long getIdVendedor() {
        return idVendedor;
    }
    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }
    public Long getCodigoVendedor() {
        return codigoVendedor;
    }
    public void setCodigoVendedor(Long codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getSueldo() {
        return sueldo;
    }
    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
}
