package spring.mongo.connector.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import spring.mongo.connector.domain.reference.Street;

@Document(collection = "LOCATION")
public class Location extends PersistentObject{

	@DBRef
	@Indexed
	private Street street;
	@Indexed
	private String zipCode;
	
	private double[] location;

	@PersistenceConstructor
	public Location(String id, Street street, String zipCode, double[] location) {
		super(id);
		this.street = street;
		this.zipCode = zipCode;
		this.location = location;
	}
	
	public Location(String id, Street street, String zipCode, double x, double y) {
		super(id);
		this.street = street;
		this.location = new double[]{x, y};
		this.zipCode = zipCode;
	}
	
	public String zipCode(){
		return this.zipCode;
	}

	public Street getStreet() {
		return street;
	}

	public double[] getLocation() {
		return location;
	}

}
