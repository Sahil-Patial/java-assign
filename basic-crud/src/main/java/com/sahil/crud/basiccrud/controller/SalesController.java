package com.sahil.crud.basiccrud.controller;

import java.util.List;

import com.sahil.crud.basiccrud.model.Sales;
import com.sahil.crud.basiccrud.service.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/demo")
public class SalesController {
    
    private SalesService salesService;

    @Autowired
    public SalesController(SalesService thesaleservice){
        salesService = thesaleservice;
    }

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public ResponseEntity<List<Sales>> findAlls(){
        System.out.println(salesService.findAllSales().size());
        return new ResponseEntity<List<Sales>>(salesService.findAllSales(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sales", method = RequestMethod.POST)
    public Sales addSales(@RequestBody Sales theSales){
        return(salesService.saveSales(theSales));
    }

    @RequestMapping(value = "/sales", method = RequestMethod.PUT)
    public Sales updatSales(@RequestBody Sales theSales){
        Sales sales = salesService.findSalesById(theSales.getSalesId());

        if(sales == null) {
            throw new RuntimeException("Sale to update doesn't exist");
        }

        return (salesService.saveSales(theSales));
    }

    @RequestMapping(value = "/sales/{salesId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int salesId){
        Sales tempSales = salesService.findSalesById(salesId);

        if(tempSales == null){
            throw new RuntimeException("Sales Id not found");
        }
        salesService.deleteSalesById(salesId);

        return "Deleted sales id " + salesId;
    }

}
