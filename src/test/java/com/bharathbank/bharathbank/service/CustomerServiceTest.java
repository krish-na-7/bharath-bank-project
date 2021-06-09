package com.bharathbank.bharathbank.service;

import com.bharathbank.bharathbank.entity.Account;
import com.bharathbank.bharathbank.entity.Customer;

import com.bharathbank.bharathbank.repository.AccountRepository;
import com.bharathbank.bharathbank.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void findCustomerByIdTest(){

        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1,12345,"individual",2000);
        cust1accounts.add(acct1);
        Customer customerList = new Customer(1,"Ram",23,"Bangalore","Karnataka",cust1accounts);


        when(customerRepository.findById(1)).thenReturn(Optional.of(customerList));
        Customer customer = customerService.findCustomerById(1).get();

        assertEquals(1,customer.getCustomerId());
        assertEquals("Ram",customer.getCustomerName());
    }

    @Test
    public void findAllCustomerTest() {

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
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> customerList = customerService.findAllCustomer();

        assertEquals(2,customerList.size());
    }

    @Test
    public void createOrUpdateTest() {
        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1,12345,"individual",2000);
        cust1accounts.add(acct1);
        Customer customer = new Customer(1,"Ram",23,"Bangalore","Karnataka",cust1accounts);
        customerService.createOrUpdate(customer);
        verify(customerRepository,times(1)).save(customer);
    }

    @Test
    public void deleteByIdTest() {

        customerService.deleteById(1);

        verify(customerRepository,times(1)).deleteById(1);
    }

    @Test
    public void deleteAllTest() {

        customerService.deleteAll();

        verify(customerRepository,times(1)).deleteAll();

    }

    @Test
    public void getBalanceTest() {

        Account acct1 = new Account(1,12345,"individual",2000);

        when(accountRepository.findById(1)).thenReturn(Optional.of(acct1));
        Account account = accountRepository.findById(1).get();
        assertEquals(2000,account.getAccountBal());
    }
}