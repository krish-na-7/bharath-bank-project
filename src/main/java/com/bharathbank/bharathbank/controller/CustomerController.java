package com.bharathbank.bharathbank.controller;

import com.bharathbank.bharathbank.entity.Account;
import com.bharathbank.bharathbank.entity.Customer;
import com.bharathbank.bharathbank.exception.InsufficientBalanceException;
import com.bharathbank.bharathbank.exception.ResourceNotFoundException;
import com.bharathbank.bharathbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Value("${WELCOME_MESSAGE}")
    String message;

    @GetMapping("/message")
    public String getMessage(){
        return message;
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId)throws ResourceNotFoundException {
        return Optional.ofNullable(this.customerService.findCustomerById(customerId)).get().orElseThrow(() -> new ResourceNotFoundException("Customer Not Found With Given ID"));
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
        return this.customerService.findAllCustomer();
    }

    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer customer){
        Customer cust = this.customerService.createOrUpdate(customer);
        return cust;
    }

    @PutMapping({"/customer/{customerId}"})
    public Customer updateCustomerById(@PathVariable  int customerId,@RequestBody Customer customer) throws ResourceNotFoundException{
       Customer cust =  Optional.ofNullable(this.customerService.findCustomerById(customerId)).get().orElseThrow(() -> new ResourceNotFoundException("Customer Not Found With Given ID"));
       return this.customerService.createOrUpdate(cust);
    }

    @DeleteMapping("/customer/{customerId}")
    public String deleteById(@PathVariable int customerId) throws ResourceNotFoundException{
        Customer cust =Optional.ofNullable(this.customerService.findCustomerById(customerId)).get().orElseThrow(() -> new ResourceNotFoundException("Customer Not Found With Given ID"));
        return this.customerService.deleteById(customerId);
    }

    @PutMapping("/customer/{senderId}/{receiverId}/{amount}")
    public Optional<Account> amountTransfer(@PathVariable int senderId, @PathVariable int receiverId, @PathVariable int amount) throws ResourceNotFoundException, InsufficientBalanceException {
        Customer sender = Optional.ofNullable(this.customerService.findCustomerById(senderId)).get().orElseThrow(() -> new ResourceNotFoundException("Customer Not Found With Given ID"));
        Customer receiver = Optional.ofNullable(this.customerService.findCustomerById(receiverId)).get().orElseThrow(() -> new ResourceNotFoundException("Customer Not Found With Given ID"));
        int senderAmount = this.customerService.getBalance(senderId);
        if(sender==null || receiver==null){
            throw new ResourceNotFoundException("Customer with given ID does not exist");
        }
        else if(senderAmount<amount){
            throw new InsufficientBalanceException("Insufficient balance in sender account");
        }
        else {
            return this.customerService.amountTransfer(senderId,receiverId,amount);
        }
    }
    @DeleteMapping("/customer")
    public String deleteAllCustomers(){
        return customerService.deleteAll();
    }
}
