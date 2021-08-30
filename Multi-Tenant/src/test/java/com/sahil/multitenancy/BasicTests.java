package com.sahil.multitenancy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import com.sahil.multitenancy.model.Customer;
import com.sahil.multitenancy.repositories.ICustomerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

@SpringBootTest
public class BasicTests {

    @Autowired
    private ICustomerRepository repo;

    @Test
    @Transactional
    public void testSave()  {

        Customer c = new Customer();
        HttpHeaders h = new HttpHeaders();
        h.set("X-TenantID", "TenantOne");
        

        c.setId(7L);
        c.setFirstName("pehla");
        c.setLastName("akhiri");

        repo.save(c);   
        assertNotNull(repo.findById(7L).get());
    }
    
}
