package com.bharathbank.bharathbank.service;

import com.bharathbank.bharathbank.entity.Account;
import com.bharathbank.bharathbank.entity.Customer;
import com.bharathbank.bharathbank.exception.ResourceNotFoundException;
import com.bharathbank.bharathbank.repository.AccountRepository;
import com.bharathbank.bharathbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    public Optional<Customer> findCustomerById(int customerId) {
       return Optional.ofNullable(this.customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found With Given ID")));
    }

    public List<Customer> findAllCustomer(){
        List<Customer> customers = new ArrayList<>();
        customers.addAll(this.customerRepository.findAll());
        return customers;
    }

    public Customer createOrUpdate(Customer customer){

        Optional<Customer> cache = this.customerRepository.findById(customer.getCustomerId());

        if(cache.isPresent()){
            Customer cust = cache.get();
            cust.setCustomerName(customer.getCustomerName());
            cust.setCustomerAge(customer.getCustomerAge());
            cust.setCustomerCity(customer.getCustomerCity());
            cust.setCustomerState(customer.getCustomerState());
            cust=this.customerRepository.save(cust);
            return cust;
        }
        else {
            this.customerRepository.save(customer);
            return customer;
        }
    }

    public String deleteById(int customerId){
        this.customerRepository.deleteById(customerId);
        return "Customer Deleted With Given ID";
    }

    public String deleteAll(){
        this.customerRepository.deleteAll();
        return "Deleted all customers";
    }

    public Optional<Account> amountTransfer(int senderId, int receiverId, int amount){
        Account senderCustomer = this.accountRepository.findById(senderId).get();
        Account receiverCustomer = this.accountRepository.findById(receiverId).get();
        int senderAmount = senderCustomer.getAccountBal();
        int receiverAmount = receiverCustomer.getAccountBal();

        int remainingAmount = senderAmount - amount;

        int addedAmount = receiverAmount + amount;
        senderCustomer.setAccountBal(remainingAmount);
        receiverCustomer.setAccountBal(addedAmount);

        senderCustomer = this.accountRepository.save(senderCustomer);
        receiverCustomer = this.accountRepository.save(receiverCustomer);

        return Optional.ofNullable(senderCustomer);

    }

    public int getBalance(int customerId){
        Account accountInfo = this.accountRepository.findById(customerId).get();
        int senderAmount = accountInfo.getAccountBal();
        return senderAmount;
    }
}
