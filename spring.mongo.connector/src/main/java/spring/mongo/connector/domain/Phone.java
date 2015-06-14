package spring.mongo.connector.domain;

import spring.mongo.connector.domain.reference.PhoneType;

public abstract class Phone extends PersistentObject{
	
	private PhoneType type;
	private String phone;
	
	public Phone(String id, PhoneType type) {
		super(id);
		this.type = type;
	}

	public PhoneType getType() {
		return type;
	}

	public String getPhone() {
		return phone;
	}
	
}
