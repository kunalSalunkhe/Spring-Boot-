package com.first.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.demo.AccountRepo;
import com.first.demo.model.Account;
import com.first.demo.model.Customer;
import com.first.demo.repo.CustomerRepo;

@RestController
@RequestMapping("bank")
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {

	@Autowired(required = true)
	CustomerRepo cr1;

	@Autowired(required = true)
	AccountRepo ar1;

	// pagination
//	@GetMapping("/customers")
	@Transactional
	List<Customer> getAllCustomers(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);

//		List<Customer> pl = cr1.findAll();

//		List<Customer> p = pl.stream().skip(page * size).limit(size).collect(Collectors.toList());

		Page<Customer> cp = cr1.findAll(pageable);

		List<Customer> c = new ArrayList<>(cp.getContent());

		String s = "name";
		System.out.println(c);
		if (s.equalsIgnoreCase("name")) {
			Collections.sort(c, new sortByName());
		}
		System.out.println(c);

		return c;
	}

	// sorting
	@GetMapping("/customers")
	@Transactional
	List<Customer> getAllCustomersSort(@RequestParam(defaultValue = "customerId") String sortBy) {

		Sort sort = Sort.by(sortBy);

		List<Customer> unsortedList = cr1.findAll().stream().limit(5).collect(Collectors.toList());
		
		System.out.println(unsortedList);
		List<Customer> list = cr1.findAll(sort).stream().limit(5).collect(Collectors.toList());
		
		System.out.println(list);

		return list;
	}

//	@GetMapping("/customers")
	public Page<Customer> getSortedAndPaginatedPeople(@RequestParam int page, @RequestParam int size,
			@RequestParam String sortByField) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortByField));
		return cr1.findAll(pageable);
	}

	@PostMapping("/login")
	@Transactional
	ResponseEntity<?> loginCustomers(@RequestBody Customer customer, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		if (customer != null && !StringUtils.isEmpty(customer.getUserId())
				&& !StringUtils.isEmpty(customer.getPassword())) {

			Customer customerEO = cr1.findByUserIdAndPassword(customer.getUserId(), customer.getPassword());
			System.out.println(customerEO);
			if (customerEO.getRole().equalsIgnoreCase("admin")) {
				Pageable pageable = PageRequest.of(page, size);

				return new ResponseEntity<List<Customer>>(cr1.findAll(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Account>>(ar1.findAllByCustomerId(customerEO.getCustomerId()),
						HttpStatus.OK);
			}

		}
		return null;

	}

//	@GetMapping("/customers")
//	ResponseEntity<List<Customer>> getAllCustomers(){
//		System.out.println("inside Customers");
//		return ResponseEntity.ok(cr1.findAll());
//	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			Customer cust = cr1.save(customer);
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);
		} catch (RuntimeException e) {
			if (e.getMessage().contains("Duplicate entry")
					|| e.getMessage().contains("constraint [customer.PRIMARY]")) {
				int lastCustId = cr1.getLastCustId();
				customer.setCustomerId(lastCustId + 1);
				Customer cust = cr1.save(customer);
				return new ResponseEntity<Customer>(cust, HttpStatus.OK);
			} else {
				return new ResponseEntity<RuntimeException>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PutMapping("/editCustomer")
	public ResponseEntity<?> editCustomer(@RequestBody Customer customer) {

		Optional<Customer> cust = cr1.findById(customer.getCustomerId());
		Customer c = cust.get();
		c.setAddress(customer.getAddress());
		c.setContactNo(customer.getContactNo());
		c.setName(customer.getName());
		cr1.save(c);

		return new ResponseEntity<Customer>(c, HttpStatus.OK);

	}

	@GetMapping("customerAccounts/{customerId}")
	List<Account> getAllAccounts(@PathVariable("customerId") int customerId) {

		return ar1.findAllByCustomerId(customerId);
	}

}
