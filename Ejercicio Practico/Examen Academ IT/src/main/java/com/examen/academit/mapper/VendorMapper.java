package com.examen.academit.mapper;

import com.examen.academit.dto.Vendor;
import com.examen.academit.entities.Vendedor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VendorMapper {
    //Realizado con MapperStruct
    //Mapeo de Vendedor a Vendor:
    @Mappings({
            @Mapping(source = "codigoVendedor", target = "code"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "sueldo", target = "salary"),
    })
    Vendor toVendor(Vendedor vendedor);
    List<Vendor> toVendor(List<Vendedor> vendedor);

    //Mapeo de Vendor a Vendedor:
    @InheritInverseConfiguration
//    @Mapping(target = "idVendedor", ignore = true)
    Vendedor toVendedor(Vendor vendor);
    List<Vendedor> toVendedor(List<Vendor> vendors);
}
