package com.bharathbank.bharathbank.controller;

import com.bharathbank.bharathbank.entity.Account;
import com.bharathbank.bharathbank.entity.Customer;
import com.bharathbank.bharathbank.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomerByIdTest() throws Exception {
        List<Account> cust1accounts = new ArrayList<>();
        Account acct1 = new Account(1, 12345, "individual", 2000);
        cust1accounts.add(acct1);
        Customer cust1 = new Customer(1, "Ram", 23, "Bangalore", "Karnataka", cust1accounts);
        when(customerService.findCustomerById(1)).thenReturn(java.util.Optional.of(cust1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/{customerId}", 1).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().json("{\n" +
                "    \"customerId\": 1,\n" +
                "    \"customerName\": \"Ram\",\n" +
                "    \"customerAge\": 23,\n" +
                "    \"customerCity\": \"Bangalore\",\n" +
                "    \"customerState\": \"Karnataka\",\n" +
                "    \"account\": [\n" +
                "        {\n" +
                "            \"customerId\": 1,\n" +
                "            \"accountNo\": 12345,\n" +
                "            \"accountType\": \"individual\",\n" +
                "            \"accountBal\": 2000\n" +
                "        }\n" +
                "    ]\n" +
                "}")).andReturn();

    }

   @Test
   public void getAllCustomersTest() throws Exception {

       List<Account> cust1accounts = new ArrayList<>();
       Account acct1 = new Account(1, 12345, "individual", 2000);
       cust1accounts.add(acct1);
       Customer cust1 = new Customer(1, "Ram", 23, "Bangalore", "Karnataka", cust1accounts);
       List<Account> cust2accounts = new ArrayList<>();
       Account acct2 = new Account(2, 67890, "individual", 3000);
       cust2accounts.add(acct2);
       Customer cust2 = new Customer(2, "Sita", 21, "Hyderabad", "Telangana", cust2accounts);
       List<Customer> customers = new ArrayList<>();
       customers.add(cust1);
       customers.add(cust2);

       when(customerService.findAllCustomer()).thenReturn(customers);

       RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer").accept(MediaType.APPLICATION_JSON);
       mockMvc.perform(requestBuilder).andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$[*].customerId").isNotEmpty());
    }

    @Test
    public void deleteAllCustomersTest() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/");
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    @Test
    public void deleteByIdTest() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/{customerId}");
        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }

}