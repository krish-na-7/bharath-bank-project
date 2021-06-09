package com.bharathbank.bharathbank.repository;

import com.bharathbank.bharathbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends JpaRepository<Account,Integer> {

}
