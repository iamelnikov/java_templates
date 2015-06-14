package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_POSITION")
public class Position extends AbstractReference{

	public Position(String id, String value) {
		super(id, value);
	}
}
