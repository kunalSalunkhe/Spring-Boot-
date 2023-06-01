package com.first.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.first.demo.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer>{

	@Query(value="select customer_id from customer order by customer_id desc limit 0,1", nativeQuery=true)
	public int getLastCustId();
	
	public Customer findByUserIdAndPassword(String userId, String password);
	
}
