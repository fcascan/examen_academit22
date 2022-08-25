package com.examen.academit.repositories;

import com.examen.academit.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<List<Venta>> findVentaByVendedor_CodigoVendedor(Long cod);
    void deleteVentaByIdVenta(Long id);
}
