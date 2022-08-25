package com.examen.academit.mapper;

import com.examen.academit.dto.Sale;
import com.examen.academit.entities.Venta;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {ProductMapper.class, VendorMapper.class})
public interface SaleMapper {
    //Realizado con MapperStruct
    Sale toSale(Sale sale);

    //Mapeo de Venta a Sale:
    @Mappings({
//            @Mapping(target = "idSale", ignore = true),
            @Mapping(source = "codigoVenta", target = "code"),
            @Mapping(source = "monto", target = "total"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "producto", target = "product"),
            @Mapping(source = "vendedor", target = "vendor")
//            @Mapping(target = "product.setCode(producto.getCodigoProducto())"),
//            @Mapping(target = "product.setName(producto.getNombre())"),
//            @Mapping(target = "product.setPrice(producto.getPrecio())"),
//            @Mapping(target = "product.setCategory(producto.getCategoria())"),
//            @Mapping(target = "vendor.setCode(vendedor.getCodigoProducto())"),
//            @Mapping(target = "vendor.setName(vendedor.getNombre())"),
//            @Mapping(target = "vendor.setSalary(vendedor.getSueldo())")
    })
    Sale toSale(Venta venta);
    List<Sale> toSale(List<Venta> ventas);

    //Mapeo de Sale a Venta:
    @Mappings({
            @Mapping(target = "idVenta", ignore = true),
            @Mapping(source = "code", target = "codigoVenta"),
            @Mapping(source = "total", target = "monto"),
            @Mapping(source = "quantity", target = "cantidad"),
            @Mapping(source = "product", target = "producto"),
            @Mapping(source = "vendor", target = "vendedor")
//            @Mapping(target = "producto.setCodigoProducto(product.getCode())"),
//            @Mapping(target = "producto.setNombre(product.getName())"),
//            @Mapping(target = "producto.setPrecio(product.getPrice())"),
//            @Mapping(target = "producto.setCategoria(product.getCategory())"),
//            @Mapping(target = "vendedor.setCodigoVendedor(vendor.getCode())"),
//            @Mapping(target = "vendedor.setNombre(vendor.getName())"),
//            @Mapping(target = "vendedor.setSueldo(vendor.getSalary())")
    })
    Venta toVenta(Sale sales);
    List<Venta> toVenta(List<Sale> sales);
}
