package com.examen.academit.services;

import com.examen.academit.dto.Sale;
import com.examen.academit.entities.Venta;
import com.examen.academit.exceptions.BadRequestException;
import com.examen.academit.mapper.SaleMapper;
import com.examen.academit.repositories.ProductoRepository;
import com.examen.academit.repositories.VendedorRepository;
import com.examen.academit.repositories.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {
    //Atributos:
    private final VentaRepository ventaRepository;
    private final VendedorRepository vendedorRepository;
    private final ProductoRepository productoRepository;
    private final SaleMapper saleMapper;

    //Constructor:
    public VentaService(VentaRepository ventaRepository, VendedorRepository vendedorRepository, ProductoRepository productoRepository, SaleMapper saleMapper) {
        this.ventaRepository = ventaRepository;
        this.vendedorRepository = vendedorRepository;
        this.productoRepository = productoRepository;
        this.saleMapper = saleMapper;
    }

    //Metodos:
    @Override
    public Optional<List<Sale>> getAllSales(){
        List<Venta> sl = this.ventaRepository.findAll();
        if(!sl.isEmpty()) {
            return Optional.of(this.saleMapper.toSale(sl));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Sale>> getSalesByVendorCode(Long cod){
        Optional<List<Venta>> vl = this.ventaRepository.findVentaByVendedor_CodigoVendedor(cod);
        if(vl.isPresent()) {
            return Optional.of(saleMapper.toSale(vl.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Sale> addNewSale(Sale s) throws BadRequestException{
        Venta v = saleMapper.toVenta(s);
        if(!(this.productoRepository.findByCodigoProducto(v.getProducto().getCodigoProducto())).isPresent()){
            throw new BadRequestException("No existe el codigo de producto vendido en la base de datos.");
        }
        if(!(this.vendedorRepository.findByCodigoVendedor(v.getVendedor().getCodigoVendedor())).isPresent()){
            throw new BadRequestException("No existe el codigo del vendedor asignado en la base de datos.");
        }
        //Agrego el ID del Producto:
        v.getProducto().setIdProducto(
                this.productoRepository.findByCodigoProducto(v.getProducto().getCodigoProducto())
                        .get().getIdProducto()
        );
        //Agrego el ID del Vendedor:
        v.getVendedor().setIdVendedor(
                this.vendedorRepository.findByCodigoVendedor(v.getVendedor().getCodigoVendedor())
                        .get().getIdVendedor()
        );
        return Optional.of(saleMapper.toSale(this.ventaRepository.save(v)));
    }

    @Override
    public Double calculateVendorCommission(Long vendorCode){
        Optional<List<Venta>> vl = this.ventaRepository.findVentaByVendedor_CodigoVendedor(vendorCode);
        if(!vl.isPresent()) return 0.0;
        double acum = 0;
        for(int i = 0; i < (long) vl.get().size(); i++){
            if(vl.get().get(i).getCantidad() >= 3){
                //Si se venden 3 o mas productos: se calcula un 10% de comision de venta
                acum += vl.get().get(i).getMonto() * 0.1;
            } else {
                //Si se venden 2 o menos productos: se calcula un 5% de comision de venta
                acum += vl.get().get(i).getMonto() * 0.05;
            }
        }
        return acum;
    }

//    @Override
//    public Optional<Sale> updateSale(Sale s){
//        Optional<Producto> result = this.repository.findByCodigoProducto(p.getCode());
//        if(result.isPresent() && Objects.equals(result.get().getCodigoProducto(), p.getCode())) {
//            Producto newP = new Producto(result.get().getIdProducto(), p.getCode(), p.getName(),
//                                         p.getPrice(), p.getCategory(), saleMapper.toVenta(p.getSales()));
//            return Optional.of(mapper.toProduct(this.repository.save(newP)));
//        } else {
//        return Optional.empty();
//        }
//    }

//    @Override
//    public Boolean deleteSale(Long code){
//        Optional<Venta> v = this.ventaRepository.findByCodigoProducto(code);
//        if(v.isPresent()) {
//            this.ventaRepository.delete(v.get());
//            return true;
//        } else {
//            return false;
//        }
//    }
}
