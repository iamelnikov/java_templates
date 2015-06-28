package ru.imelnikov.template.concurrency;

public class Address {
	private City city;
	private String zipCode;
	private String street;
	private String house;
	private String builing;
	Address(City city, String zipCode, String street, String house, String building) {
		super();
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.house = house;
		this.builing = building;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getBuiling() {
		return builing;
	}
	public void setBuiling(String builing) {
		this.builing = builing;
	}	
}
