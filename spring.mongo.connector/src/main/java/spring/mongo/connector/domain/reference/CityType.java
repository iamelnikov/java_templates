package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_CITY_TYPE")
public class CityType extends AbstractReference{

	public CityType(String id, String value) {
		super(id, value);
	}
}
