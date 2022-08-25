package com.examen.academit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "productos")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    @Column(name = "codigo_producto", unique = true, nullable = false)
    private Long codigoProducto;
    private String nombre;
    private Double precio;
    private String categoria;

    //Constructores:
    public Producto() {
    }
    public Producto(Long idProducto, Long codigoProducto, String nombre, Double precio, String categoria) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    //Getters y Setters:
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Long getCodigoProducto() {
        return codigoProducto;
    }
    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
