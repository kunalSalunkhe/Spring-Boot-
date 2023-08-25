package com.first.demo.controller;

import java.util.Comparator;

import com.first.demo.model.Customer;

public class sortByName implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
