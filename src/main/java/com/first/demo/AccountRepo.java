package com.first.demo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.first.demo.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

	@Query(value = "select account_no ,account_holder from bank_account", nativeQuery = true)
	
	List<ArrayList> getAccounts();

	@Query(value = "select account_holder from bank_account  where account_no = :accountId",nativeQuery=true)
	String getAccountHolder(@Param("accountId") int accountId);

//	@Query(value = "select account_balance from bank_account  where accountNo = :accountId",nativeQuery=true)
	List<Account> findByAccountNo(int acc);
//	float getAccountBalance(@Param("accountId") int accountId);

	@Query(value = "select * from bank_account where customer_id = :customerId", nativeQuery = true)
	List<Account> findAllByCustomerId(@Param("customerId") int customerId);

//	List<Account> findByCustomerIdAndAccountNo(int cc,int ac);
	
	@Transactional
	@Modifying
	@Query(value = "update bank_account set account_balance = :balance where account_no = :accountId",nativeQuery=true)
	void setAccountBalance(@Param("balance")float balance, @Param("accountId")int accountId);

}
