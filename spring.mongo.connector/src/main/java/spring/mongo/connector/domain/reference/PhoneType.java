package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_PHONE_TYPE")
public class PhoneType extends AbstractReference{

	public PhoneType(String id, String value) {
		super(id, value);
	}
}
