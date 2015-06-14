package spring.mongo.connector.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class UserRole extends PersistentObject {

	@Field(value = "name")
	private String name;

	UserRole(String id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
