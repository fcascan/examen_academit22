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
@Table(name = "ventas")
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;
    @Column(name = "codigo_venta", unique = true, nullable = false)
    private Long codigoVenta;
    private Double monto;
    private Integer cantidad;

    //Relacion con Producto: Muchas ventas, cada una asociada a solo un producto
    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @MapsId("idVenta")
//    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_producto", insertable = false, updatable = false)
    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_producto")
    private Producto producto;

    //Relacion con Vendedor: Muchas ventas, cada una asociada a solo un vendedor
    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @MapsId("idVenta")
//    @JoinColumn(name = "codigo_vendedor", referencedColumnName = "codigo_vendedor", insertable = false, updatable = false)
    @JoinColumn(name = "codigo_vendedor", referencedColumnName = "codigo_vendedor")
    private Vendedor vendedor;

    //Constructores:
    public Venta() {
    }
    public Venta(Long idVenta, Long codigoVenta, Double monto, Integer cantidad, Producto producto, Vendedor vendedor) {
        this.idVenta = idVenta;
        this.codigoVenta = codigoVenta;
        this.monto = monto;
        this.cantidad = cantidad;
        this.producto = producto;
        this.vendedor = vendedor;
    }

    //Getters y Setters:
    public Long getCodigoVenta() {
        return codigoVenta;
    }
    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }
    public Long getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Vendedor getVendedor() {
        return vendedor;
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
