package com.sahil.crud.basiccrud.DAO;

import java.util.*;

import com.sahil.crud.basiccrud.model.Sales;

public interface SalesDAO {
    List<Sales> getAllSales();

    Sales findSalesById(int theId);

    Sales saveSales(Sales theSales);

    void deleteSalesById(int theId);
}
