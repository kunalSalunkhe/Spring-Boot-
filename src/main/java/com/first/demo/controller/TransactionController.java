package com.first.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.first.demo.AccountRepo;
import com.first.demo.model.Transaction;
import com.first.demo.repo.TransactionRepo;

@RestController
@RequestMapping("bank/account/transaction")
public class TransactionController {

	@Autowired(required=true)
	TransactionRepo tr1;
	
	@Autowired
	AccountRepo ar1;

	@ResponseBody
	@PutMapping("deposit/{accountId}")	
	Map <Integer,Float> addDeposit(@RequestBody Transaction t1, @PathVariable("accountId")int accountId) {
	
		//updateBalance
		float balance = ar1.findByAccountNo(accountId).get(0).getAccountBalance();
		balance += t1.getDeposit();
		ar1.setAccountBalance(balance,accountId);
		t1.setBalance(balance);
		
		
		//addNewTransaction-Id
		int transactionId = tr1.getLastTransactionId();
		t1.setTransacionId(++transactionId);

		//addAcccountId
		t1.setAccount(ar1.findById(accountId).orElse(null));
		t1.getAccount().setAccountNo(accountId);	
		
		//addNewTransaction
		tr1.save(t1);
		
		Map<Integer,Float> ab = new HashMap<>();
		
		ab.put(accountId, balance);
		
		return ab;
		
	}
	
	@ResponseBody
	@PutMapping("withdrawal/{accountId}")		
	Map<Integer,Float> withdrawal(@RequestBody Transaction t1, @PathVariable("accountId") int accountId) {
		
		//updateBalance
		float balance = ar1.findByAccountNo(accountId).get(0).getAccountBalance();
		
		if(t1.getWithdrawal() < balance) {
			balance -= t1.getWithdrawal();
			ar1.setAccountBalance(balance, accountId);
			t1.setBalance(balance);
		}else {
			Map<Integer,Float> ab = new HashMap<>();
			
			ab.put(accountId, balance);
			
			return ab;
		}
		
		//addNewTransaction-Id
		int transactionId = tr1.getLastTransactionId();
		t1.setTransacionId(transactionId);

		//addAcccountId
		t1.setAccount(ar1.findById(accountId).orElse(null));
		t1.getAccount().setAccountNo(accountId);
				
		//addNewWithDrawal
		tr1.save(t1);
		
		Map<Integer,Float> ab = new HashMap<>();
		
			ab.put(accountId, balance);
		
		return ab;
	}
}
