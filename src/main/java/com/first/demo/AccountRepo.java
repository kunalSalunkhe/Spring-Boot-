package com.first.demo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import antlr.collections.impl.Vector;


@Repository
public interface AccountRepo extends JpaRepository<Account,Integer>{

	@Query(value="select account_no ,account_holder from account",nativeQuery=true)
	List<ArrayList> getAccounts();
	
	@Query("select accountHolder from Account  where accountNo = ?1")
	String getAccountHolder(@Param("accountId") int accountId);
	
	@Query("select accountBalance from Account  where accountNo = ?1")
	float getAccountBalance(@Param("accountId") int accountId);
	
	@Query(value="select * from account where customer_id = ?1",nativeQuery=true)
	List<Account> findAllByCustomerId(@Param("customerId") int customerId);
	
	@Transactional
	@Modifying
	@Query("update Account set accountBalance = ?1 where accountNo = ?2")
	void setAccountBalance(@Param("balance")float balance, @Param("accountId")int accountId);
	
}
