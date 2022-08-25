package com.examen.academit.services;

import com.examen.academit.dto.Vendor;
import com.examen.academit.entities.Vendedor;
import com.examen.academit.mapper.VendorMapper;
import com.examen.academit.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService implements IVendedorService {
    //Atributos:
    private final VendedorRepository repository;
    private final VendorMapper vendorMapper;

    public VendedorService(VendedorRepository repository, VendorMapper vendorMapper) {
        this.repository = repository;
        this.vendorMapper = vendorMapper;
    }

    //Metodos:
    @Override
    public Optional<List<Vendor>> getAllVendors(){
        List<Vendedor> vl = this.repository.findAll();
        if(!vl.isEmpty()) {
            return Optional.of(this.vendorMapper.toVendor(vl));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vendor> getVendorByCode(Long code){
        Optional<Vendedor> v = this.repository.findByCodigoVendedor(code);
        if(v.isPresent()) {
            return Optional.of(vendorMapper.toVendor(v.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Vendor>> getVendorByName(String name){
        Optional<List<Vendedor>> vl = this.repository.findByNombre(name);
        if(vl.isPresent()) {
            return Optional.of(vendorMapper.toVendor(vl.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vendor> addNewVendor(Vendor v){
        Optional<Vendedor> result = this.repository.findByCodigoVendedor(v.getCode());
        if(result.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(vendorMapper.toVendor(this.repository.save(vendorMapper.toVendedor(v))));
        }
    }

    @Override
    public Optional<Vendor> updateVendor(Vendor v){
//        Optional<Producto> result = this.repository.findByCodigoProducto(p.getCode());
//        if(result.isPresent() && Objects.equals(result.get().getCodigoProducto(), p.getCode())) {
//            Producto newP = new Producto(result.get().getIdProducto(), p.getCode(), p.getName(),
//                                         p.getPrice(), p.getCategory(), saleMapper.toVenta(p.getSales()));
//            return Optional.of(mapper.toProduct(this.repository.save(newP)));
//        } else {
        return Optional.empty();
//        }
    }

    @Override
    public Boolean deleteVendor(Long code){
        Optional<Vendedor> v = this.repository.findByCodigoVendedor(code);
        if(v.isPresent()) {
            this.repository.delete(v.get());
            return true;
        } else {
            return false;
        }
    }
}
