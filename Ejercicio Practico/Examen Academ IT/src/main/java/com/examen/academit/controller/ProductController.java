package com.examen.academit.controller;

import com.examen.academit.dto.Product;
import com.examen.academit.exceptions.NoContentException;
import com.examen.academit.exceptions.NotFoundException;
import com.examen.academit.exceptions.NotModifiedException;
import com.examen.academit.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductController {
    // API Rest para los productos \\
    //Atributos:
    private final ProductoService productService;

    //Constructores:
    public ProductController(ProductoService productService) {
        this.productService = productService;
    }

    //Metodos (Endpoints de la API):
    @GetMapping("/hello")
    public  ResponseEntity<?> hello(){
        //Say Hi for testing
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Hi! This is working! :D");
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        //Obtain all products from DB
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<List<Product>> lp = this.productService.getAllProducts();
            if (lp.isPresent()) {
                responseBody.put("number of results", lp.get().size());
                responseBody.put("data", lp.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (NoContentException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code){
        //Obtain a product for its code
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<Product> p = this.productService.getProductByCode(code);
            if (p.isPresent()) {
                responseBody.put("message", "Product founded");
                responseBody.put("data", p.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NotFoundException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<?> getAllByCategory(@PathVariable String cat){
        //Obtain all products from a specific category
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<List<Product>> lp = this.productService.getProductsByCategory(cat);
            if (lp.isPresent()) {
                responseBody.put("number of results", lp.get().size());
                responseBody.put("data", lp.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (NoContentException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getWithName(@PathVariable String name){
        //Obtain all products containing a name like path argument
         Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<List<Product>> lp = this.productService.getProductsContainingName(name);
            if (lp.isPresent()) {
                responseBody.put("number of results", lp.get().size());
                responseBody.put("data", lp.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (NoContentException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addNew(@RequestBody Product p){
        //Add new product to data base
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<Product> result = this.productService.addNewProduct(p);
            if (result.isPresent()) {
                responseBody.put("message", "Product added sucessfully");
                responseBody.put("data", result.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                responseBody.put("message", "Product couldn't be added to database");
                return ResponseEntity.internalServerError().body(responseBody);
            }
        } catch (NotModifiedException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Product p){
        //Update a product already in the data base
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<Product> result = this.productService.updateProduct(p);
            if (result.isPresent()) {
                responseBody.put("message", "Product updated sucessfully");
                responseBody.put("data", result.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NotModifiedException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<?> delete(@PathVariable Long code){
        //Delete a Product by its code
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Boolean result = this.productService.deleteProduct(code);
            if(result) {
                responseBody.put("message", "Product deleted sucessfully");
                return ResponseEntity.ok().body(responseBody);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NotFoundException e) {
            responseBody.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }
}
