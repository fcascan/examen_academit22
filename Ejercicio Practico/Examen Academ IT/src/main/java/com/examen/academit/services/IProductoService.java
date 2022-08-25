package com.examen.academit.services;

import com.examen.academit.dto.Product;
import com.examen.academit.exceptions.NoContentException;
import com.examen.academit.exceptions.NotFoundException;
import com.examen.academit.exceptions.NotModifiedException;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Optional<List<Product>> getAllProducts() throws NoContentException;
    Optional<Product> getProductByCode(Long code) throws NotFoundException;
    Optional<List<Product>> getProductsByCategory(String cat) throws NoContentException;
    Optional<List<Product>> getProductsContainingName(String name) throws NoContentException;
    Optional<Product> addNewProduct(Product p) throws NotModifiedException;
    Optional<Product> updateProduct(Product p) throws NotModifiedException;
    Boolean deleteProduct(Long code) throws NotFoundException;
}
