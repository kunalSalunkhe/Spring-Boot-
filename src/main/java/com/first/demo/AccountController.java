package com.first.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.impl.Vector;

@RestController
@RequestMapping("bank/account")
public class AccountController {

	@Autowired(required=true)
	AccountRepo ar1;
	
	@Autowired(required=true)
	TransactionRepo tr1;
	
	@GetMapping("allAccounts")
	List<ArrayList> getAllAccounts(){
		
		return ar1.getAccounts();
	}
	
	@GetMapping("accountBalance/{accountId}")
	 Map<String,Float> getBalance(@PathVariable("accountId") int accountId){
		
		 Map<String,Float> nb = new HashMap<>();
		 

		String name = ar1.getAccountHolder(accountId);
		float balance = ar1.getAccountBalance(accountId);
		
		nb.put(name, balance);
		
		return nb;
	}
	
	@GetMapping("allTransaction/{accountNo}")
	List<Transaction> getAllTransactions(@PathVariable("accountNo") int accountNo){
		
		return tr1.findAllByAccountId(accountNo);
	}
}
