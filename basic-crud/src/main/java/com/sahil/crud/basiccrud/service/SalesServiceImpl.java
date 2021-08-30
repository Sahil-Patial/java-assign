package com.sahil.crud.basiccrud.service;

import java.util.List;

import javax.transaction.Transactional;

import com.sahil.crud.basiccrud.DAO.SalesDAO;
import com.sahil.crud.basiccrud.model.Sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService{
    
    SalesDAO salesDAO;
    
    @Autowired
    public SalesServiceImpl(@Qualifier("salesDAOJpaImpl") SalesDAO theSalesDAO){
        salesDAO = theSalesDAO;
    }

    @Override
    @Transactional
    public List<Sales> findAllSales() {
        
        return salesDAO.getAllSales();
    }


    @Override
    @Transactional
    public Sales findSalesById(int theId) {
        
        return salesDAO.findSalesById(theId);
    }

    @Override
    @Transactional
    public Sales saveSales(Sales theSales) {
        
        return salesDAO.saveSales(theSales);
    }

    @Override
    @Transactional
    public int deleteSalesById(int theId) {
        
        salesDAO.deleteSalesById(theId);
        return theId;
    }


}
