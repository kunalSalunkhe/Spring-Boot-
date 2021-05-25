package com.first.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank")
public class CustomerController {

	@Autowired(required=true)
	CustomerRepo cr1;
	
	@Autowired(required=true)
	AccountRepo ar1;
	
	@GetMapping("customers")
	List<Customer> getAllCustomers(){
		
		return cr1.findAll();
	}

	@GetMapping("customerAccounts/{customerId}")
	List<Account> getAllAccounts(@PathVariable("customerId") int customerId){
		
		return ar1.findAllByCustomerId(customerId);
	}
	
}
