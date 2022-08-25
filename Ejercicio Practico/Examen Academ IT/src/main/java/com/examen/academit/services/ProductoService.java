package com.examen.academit.services;

import com.examen.academit.dto.Product;
import com.examen.academit.entities.Producto;
import com.examen.academit.exceptions.NoContentException;
import com.examen.academit.exceptions.NotFoundException;
import com.examen.academit.exceptions.NotModifiedException;
import com.examen.academit.mapper.ProductMapper;
import com.examen.academit.mapper.SaleMapper;
import com.examen.academit.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoService implements IProductoService {
    //Atributos:
    private final ProductoRepository repository;
    private final ProductMapper mapper;
    private final SaleMapper saleMapper;

    public ProductoService(ProductoRepository repository, ProductMapper mapper, SaleMapper saleMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.saleMapper = saleMapper;
    }

    //Metodos:
    @Override
    public Optional<List<Product>> getAllProducts() throws NoContentException {
        List<Producto> pl = this.repository.findAll();
        if(!pl.isEmpty()) {
            return Optional.of(this.mapper.toProduct(pl));
        } else {
            throw new NoContentException("No hay contenido en la base de datos para mostrar.");
        }
    }

    @Override
    public Optional<Product> getProductByCode(Long code) throws NotFoundException {
        Optional<Producto> p = this.repository.findByCodigoProducto(code);
        if(p.isPresent()) {
            return Optional.of(mapper.toProduct(p.get()));
        } else {
            throw new NotFoundException("No se ha encontrado un producto con el codigo pedido.");
        }
    }

    @Override
    public Optional<List<Product>> getProductsByCategory(String cat) throws NoContentException {
        List<Producto> pl = this.repository.findByCategoriaOrderByCategoria(cat);
        if(!pl.isEmpty()) {
            return Optional.of(mapper.toProduct(pl));
        } else {
            throw new NoContentException("No hay contenido en la base de datos para mostrar.");
        }
    }

    @Override
    public Optional<List<Product>> getProductsContainingName(String name) throws NoContentException {
        List<Producto> pl = this.repository.findByNombreContainingIgnoreCase(name);
        if(!pl.isEmpty()) {
            return Optional.of(mapper.toProduct(pl));
        } else {
            throw new NoContentException("No hay contenido en la base de datos para mostrar.");
        }
    }

    @Override
    public Optional<Product> addNewProduct(Product p) throws NotModifiedException {
        Optional<Producto> result = this.repository.findByCodigoProducto(p.getCode());
        if(result.isPresent()) {
            throw new NotModifiedException("No se ha podido cargar en la base de datos el producto solicitado.");
        } else {
            return Optional.of(mapper.toProduct(this.repository.save(mapper.toProducto(p))));
        }
    }

    @Override
    public Optional<Product> updateProduct(Product p) throws NotModifiedException{
//        Optional<Producto> result = this.repository.findByCodigoProducto(p.getCode());
//        if(result.isPresent() && Objects.equals(result.get().getCodigoProducto(), p.getCode())) {
//            Producto newP = new Producto(result.get().getIdProducto(), p.getCode(), p.getName(),
//                                         p.getPrice(), p.getCategory(), saleMapper.toVenta(p.getSales()));
//            return Optional.of(mapper.toProduct(this.repository.save(newP)));
//        } else {
            throw new NotModifiedException("No se ha podido modificar en la base de datos el producto solicitado.");
//        }
    }

    @Override
    public Boolean deleteProduct(Long cod) throws NotFoundException {
        Optional<Producto> p = this.repository.findByCodigoProducto(cod);
        if(p.isPresent()) {
            this.repository.delete(p.get());
            return true;
        } else {
            throw new NotFoundException("No se encuentra el producto en la base de datos.");
        }
    }
}
