package com.examen.academit.services;

import com.examen.academit.dto.Sale;
import com.examen.academit.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface IVentaService {
    Optional<List<Sale>> getAllSales();
    Optional<List<Sale>> getSalesByVendorCode(Long code);
    Optional<Sale> addNewSale(Sale s) throws BadRequestException;
    Double calculateVendorCommission(Long vendorCode);
//    Optional<Sale> updateSale(Sale s);
//    Boolean deleteSale(Long cod);
}
