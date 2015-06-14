package spring.mongo.connector.domain.reference;

import spring.mongo.connector.domain.PersistentObject;

public abstract class AbstractReference extends PersistentObject {

	protected String value;

	public AbstractReference(){
		super(null);
	}
	
	public AbstractReference(String id, String value) {
		super(id);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
