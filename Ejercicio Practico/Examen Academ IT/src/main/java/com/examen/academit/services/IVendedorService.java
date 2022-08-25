package com.examen.academit.services;

import com.examen.academit.dto.Vendor;

import java.util.List;
import java.util.Optional;

public interface IVendedorService {
    Optional<List<Vendor>> getAllVendors();
    Optional<Vendor> getVendorByCode(Long cod);
    Optional<List<Vendor>> getVendorByName(String name);
    Optional<Vendor> addNewVendor(Vendor p);
    Optional<Vendor> updateVendor(Vendor p);
    Boolean deleteVendor(Long cod);
}
