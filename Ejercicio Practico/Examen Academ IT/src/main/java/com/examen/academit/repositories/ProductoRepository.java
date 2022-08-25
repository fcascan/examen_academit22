package com.examen.academit.repositories;

import com.examen.academit.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

//    @Query("select p from Producto p where p.nombre like ?1")
    List<Producto> findByNombreContainingIgnoreCase(String name);

    Optional<Producto> findByCodigoProducto(Long cod);
    List<Producto> findByCategoriaOrderByCategoria(String cat);
    void deleteProductoByCodigoProducto(Long cod);
}
