package com.codeaches.oauth.model;

import java.io.Serializable;

public class Address implements Serializable {
	
private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String street;
    private String houseNumber;
    private String zipCode;
    
    public Address() {}

	public Address(Integer id, String street, String houseNumber, String zipCode) {
		super();
		this.id = id;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return getStreet();
	}
	
}
