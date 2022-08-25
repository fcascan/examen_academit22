package com.examen.academit.controller;

import com.examen.academit.dto.Vendor;
import com.examen.academit.services.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vendors")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class VendorController {
    // API Rest para los vendedores \\
    //Atributos:
    private final VendedorService vendorService;

    //Constructores:
    public VendorController(VendedorService vendorService) {
        this.vendorService = vendorService;
    }

    //Metodos (Endpoints de la API):
    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        //Say Hi for testing
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Hi! This is working! :D");
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        //Obtain all vendors from DB
        Optional<List<Vendor>> list = this.vendorService.getAllVendors();
        if(list.isPresent()){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("number of results", list.get().size());
            responseBody.put("data", list.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> getByCode(@PathVariable Long code){
        //Obtain a vendor for its code
        Optional<Vendor> v = this.vendorService.getVendorByCode(code);
        if(v.isPresent()){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Vendor founded");
            responseBody.put("data", v.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getAllByName(@PathVariable String name){
        //Obtain all vendors with a specific name
        Optional<List<Vendor>> list = this.vendorService.getVendorByName(name);
        if(list.isPresent()){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("number of results", list.get().size());
            responseBody.put("data", list.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> addNew(@RequestBody Vendor v){
        //Add new vendor to data base
        Optional<Vendor> result = this.vendorService.addNewVendor(v);
        Map<String, Object> responseBody = new HashMap<>();
        if(result.isPresent()){
            responseBody.put("message", "Vendor added sucessfully");
            responseBody.put("data", result.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            responseBody.put("message", "Vendor couldn't be added to database");
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Vendor v){
        //Update a vendor already in the data base
        Optional<Vendor> result = this.vendorService.updateVendor(v);
        if(result.isPresent()){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Vendor updated sucessfully");
            responseBody.put("data", result.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/code/{code}")
    public ResponseEntity<?> delete(@PathVariable Long code){
        //Delete a Vendor by its code
        Boolean result = this.vendorService.deleteVendor(code);
        if(result){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Vendor deleted sucessfully");
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
