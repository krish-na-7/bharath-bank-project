package com.bharathbank.bharathbank.repository;


import com.bharathbank.bharathbank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
