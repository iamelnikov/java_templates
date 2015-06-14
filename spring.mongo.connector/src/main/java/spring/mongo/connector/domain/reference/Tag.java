package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_TAG")
public class Tag extends AbstractReference{

	public Tag(String id, String value) {
		super(id, value);
	}
}
