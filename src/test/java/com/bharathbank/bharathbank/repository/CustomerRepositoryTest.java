package com.bharathbank.bharathbank.repository;

import com.bharathbank.bharathbank.entity.Account;
import com.bharathbank.bharathbank.entity.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void createRepositoryTest(){

        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1,12345,"individual",2000);
        cust1accounts.add(acct1);
        Customer cust1 = new Customer(1,"Ram",23,"Bangalore","Karnataka",cust1accounts);
        List<Account> cust2accounts = new ArrayList<>();
        Account acct2 = new Account(2,67890,"individual",3000);
        cust2accounts.add(acct2);
        Customer cust2 = new Customer(2,"Sita",21,"Hyderabad","Telangana",cust2accounts);
        List<Customer> customers = new ArrayList<>();
        customers.add(cust1);
        customers.add(cust2);

        customerRepository.save(cust1);

        Assert.assertNotNull(cust1.getCustomerId());

        assertEquals(2,customers.size());

    }

    @Test
    public void getAllCustomersTest(){
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();

        assertEquals(0,customerList.size());

        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1,12345,"individual",2000);
        cust1accounts.add(acct1);
        Customer cust1 = new Customer(1,"Ram",23,"Bangalore","Karnataka",cust1accounts);
        List<Account> cust2accounts = new ArrayList<>();
        Account acct2 = new Account(2,67890,"individual",3000);
        cust2accounts.add(acct2);
        Customer cust2 = new Customer(2,"Sita",21,"Hyderabad","Telangana",cust2accounts);

        customerRepository.save(cust1);

        customerRepository.save(cust2);

        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        assertEquals(2,customers.size());

    }

    @Test
    public void deleteAllCustomersTest(){
        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1,12345,"individual",2000);
        cust1accounts.add(acct1);
        Customer cust1 = new Customer(1,"Ram",23,"Bangalore","Karnataka",cust1accounts);
        List<Account> cust2accounts = new ArrayList<>();
        Account acct2 = new Account(2,67890,"individual",3000);
        cust2accounts.add(acct2);
        Customer cust2 = new Customer(2,"Sita",21,"Hyderabad","Telangana",cust2accounts);

        customerRepository.save(cust1);
        customerRepository.save(cust2);
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        assertEquals(2,customers.size());

        customerRepository.deleteAll();
        List<Customer> customersList = (List<Customer>) customerRepository.findAll();
        assertEquals(0,customersList.size());

    }

    @Test
    public void deleteCustomerByIdTest(){
        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1,12345,"individual",2000);
        cust1accounts.add(acct1);
        Customer cust1 = new Customer(1,"Ram",23,"Bangalore","Karnataka",cust1accounts);
        customerRepository.save(cust1);
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        assertEquals(1,customers.size());
        customerRepository.deleteById(1);
        List<Customer> customersList = (List<Customer>) customerRepository.findAll();
        assertEquals(0,customersList.size());
    }
}