package com.sahil.crud.basiccrud.service;

import java.util.*;

import com.sahil.crud.basiccrud.model.Sales;

public interface SalesService {
    public List<Sales> findAllSales();

    public Sales findSalesById(int theId);

    public Sales saveSales(Sales theSales);

    public int deleteSalesById(int theId);
}
