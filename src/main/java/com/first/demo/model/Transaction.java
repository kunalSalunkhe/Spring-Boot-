package com.first.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Transaction")
public class Transaction {
	
	@Id
	int transacionId;
	Date tdate;
	String tDescription;
	String tReference;
	float deposit;
	float withdrawal;
	float balance;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="account_id" , nullable = false)
	Account account;
	
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getTransacionId() {
		return transacionId;
	}
	public void setTransacionId(int transacionId) {
		this.transacionId = transacionId;
	}
	
	public String gettDescription() {
		return tDescription;
	}
	public void settDescription(String tDescription) {
		this.tDescription = tDescription;
	}
	public String gettReference() {
		return tReference;
	}
	public void settReference(String tReference) {
		this.tReference = tReference;
	}
	public float getDeposit() {
		return deposit;
	}
	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}
	public float getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(float withdrawal) {
		this.withdrawal = withdrawal;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
}
