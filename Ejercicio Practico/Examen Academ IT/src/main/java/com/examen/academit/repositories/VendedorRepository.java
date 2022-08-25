package com.examen.academit.repositories;

import com.examen.academit.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByCodigoVendedor(Long code);
    Optional<List<Vendedor>> findByNombre(String name);
    void deleteVendedorByCodigoVendedor(Long code);
}
