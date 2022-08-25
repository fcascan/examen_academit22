package com.examen.academit.controller;

import com.examen.academit.dto.Sale;
import com.examen.academit.exceptions.BadRequestException;
import com.examen.academit.services.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sales")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200/")
public class SaleController {
    // API Rest para las ventas \\
    //Atributos:
    private final VentaService saleService;

    //Constructores:
    public SaleController(VentaService saleService) {
        this.saleService = saleService;
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
        //Obtain all sales from DB
        Optional<List<Sale>> list = this.saleService.getAllSales();
        if(list.isPresent()){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("number of results", list.get().size());
            responseBody.put("data", list.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/code/{vendorCode}")
    public ResponseEntity<?> getByVendorCode(@PathVariable Long vendorCode){
        //Obtain a sale for its vendor code
        Optional<List<Sale>> list = this.saleService.getSalesByVendorCode(vendorCode);
        if(list.isPresent()){
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("number of results", list.get().size());
            responseBody.put("data", list.get());
            return ResponseEntity.ok().body(responseBody);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> addNew(@RequestBody Sale s) {
        //Add new sale to data base
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Optional<Sale> result = this.saleService.addNewSale(s);
            if (result.isPresent()) {
                responseBody.put("message", "Sale added sucessfully");
                responseBody.put("data", result.get());
                return ResponseEntity.ok().body(responseBody);
            } else {
                responseBody.put("message", "Product couldn't be added to database");
                return ResponseEntity.internalServerError().body(responseBody);
            }
        } catch (BadRequestException e) {
            responseBody.put("message", "Product couldn't be added to database");
            return ResponseEntity.internalServerError().body(responseBody);
        }
    }

    @GetMapping("/commisions/{vendorCode}")
    public ResponseEntity<?> commisionsByVendorCode(@PathVariable Long vendorCode){
        //Calcule vendors sale commisions according to their sales
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Commisions for vendorCode=" + vendorCode +
                         " are $" + this.saleService.calculateVendorCommission(vendorCode));
        return ResponseEntity.ok().body(responseBody);
    }

//    @PutMapping()
//    public ResponseEntity<?> update(@RequestBody Sale s){
//        //Update a sale already in the data base
//        Optional<Sale> result = this.saleService.updateSale(s);
//        if(result.isPresent()){
//            Map<String, Object> responseBody = new HashMap<>();
//            responseBody.put("message", "Sale updated sucessfully");
//            responseBody.put("data", result.get());
//            return ResponseEntity.ok().body(responseBody);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @DeleteMapping("/code/{code}")
//    public ResponseEntity<?> delete(@PathVariable Long code){
//        //Delete a sale by its code
//        Boolean result = this.saleService.deleteSale(code);
//        if(result){
//            Map<String, Object> responseBody = new HashMap<>();
//            responseBody.put("message", "Sale deleted sucessfully");
//            return ResponseEntity.ok().body(responseBody);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
