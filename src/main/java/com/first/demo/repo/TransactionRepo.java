package com.first.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.first.demo.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer>{

	@Query("select balance from Transaction where account_id = ?1")
	float getAccountBalance(@Param("accountId") int accountId);
	
	@Query(value="select * from transaction where account_id = ?1" , nativeQuery=true)
	List<Transaction> findAllByAccountId(@Param("accountNo") int accountNo);

	@Query(value="select max(transacion_id) from transaction", nativeQuery=true)
	int getLastTransactionId();
}
