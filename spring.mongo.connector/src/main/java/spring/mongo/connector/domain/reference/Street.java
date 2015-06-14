package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_STREET")
public class Street extends AbstractReference{

	@DBRef
	@Indexed
	private City city;

	public Street(String id, String value, City city) {
		super(id, value);
		this.city = city;
	}

	public City getCity() {
		return city;
	}
}
