package com.first.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		float balance = ar1.getAccountBalance(accountId);
		balance += t1.deposit;
		ar1.setAccountBalance(balance,accountId);
		t1.balance = balance;
		
		
		//addNewTransaction-Id
		int transactionId = tr1.getLastTransactionId();
		t1.transacionId = ++transactionId;

		//addAcccountId
		t1.account = ar1.findById(accountId).orElse(null);
		t1.account.accountNo = accountId;
		
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
		float balance = ar1.getAccountBalance(accountId);
		
		if(t1.withdrawal < balance) {
			balance -= t1.withdrawal;
			ar1.setAccountBalance(balance, accountId);
			t1.balance = balance;
		}else {
			Map<Integer,Float> ab = new HashMap<>();
			
			ab.put(accountId, balance);
			
			return ab;
		}
		
		//addNewTransaction-Id
		int transactionId = tr1.getLastTransactionId();
		t1.transacionId = ++transactionId;

		//addAcccountId
		t1.account = ar1.findById(accountId).orElse(null);
		t1.account.accountNo = accountId;
				
		//addNewWithDrawal
		tr1.save(t1);
		
		Map<Integer,Float> ab = new HashMap<>();
		
			ab.put(accountId, balance);
		
		return ab;
	}
}
