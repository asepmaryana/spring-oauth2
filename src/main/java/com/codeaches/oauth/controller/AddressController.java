package com.codeaches.oauth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeaches.oauth.model.Address;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AddressController {
	
	private List<Address> lists;
	
	public AddressController() {
		lists = new ArrayList<>();
		lists.add(new Address(1, "Jl. Kopo", "01", "40227"));
		lists.add(new Address(2, "Jl. Rancamanyar", "64", "40375"));
	}
	
	@GetMapping("/address/lists")
	public List<Address> getAddress() {
		return lists;
	}
	
	@PostMapping("/address/add")
	@PreAuthorize("hasAuthority('INVENTORY_CREATE')")
	public ResponseEntity<String> create(@RequestBody Address address) {
		String result = "OK";
		if(lists != null) lists.add(address);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
}
