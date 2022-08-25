package com.examen.academit.mapper;

import com.examen.academit.dto.Product;
import com.examen.academit.entities.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {
    //Realizado con MapperStruct
    //Mapeo de Producto a Product:
    @Mappings({
            @Mapping(source = "codigoProducto", target = "code"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "precio", target = "price"),
            @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Producto producto);
    List<Product> toProduct(List<Producto> productos);

    //Mapeo de Product a Producto:
    @InheritInverseConfiguration
//    @Mapping(target = "idProducto", ignore = true)
    Producto toProducto(Product product);
    List<Producto> toProducto(List<Product> products);
}
