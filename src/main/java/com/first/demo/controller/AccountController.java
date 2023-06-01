package com.first.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.demo.AccountRepo;
import com.first.demo.model.Account;
import com.first.demo.model.Transaction;
import com.first.demo.repo.TransactionRepo;

@RestController
@RequestMapping("bank/account")
@CrossOrigin(origins="http://localhost:4200/")
public class AccountController {

	@Autowired(required=true)
	AccountRepo ar1;
	
	@Autowired(required=true)
	TransactionRepo tr1;
	
	@GetMapping("allAccounts/{custId}")
	List<Account> getAllAccounts(@PathVariable("custId") int custId){
		System.out.println("inside getAllAccounts" +custId);
		return ar1.findAllByCustomerId(custId);
	}
	
	@GetMapping("accountBalance/{accountId}")
	 Map<String,Float> getBalance(@PathVariable("accountId") int accountId){
		
		 Map<String,Float> nb = new HashMap<>();
		 

		String name = ar1.getAccountHolder(accountId);
		float balance = ar1.findByAccountNo(accountId).get(0).getAccountBalance();
		
		nb.put(name, balance);
		
		return nb;
	}
	
	@GetMapping("allTransaction/{accountNo}")
	List<Transaction> getAllTransactions(@PathVariable("accountNo") int accountNo){
		
		return tr1.findAllByAccountId(accountNo);
	}
}
