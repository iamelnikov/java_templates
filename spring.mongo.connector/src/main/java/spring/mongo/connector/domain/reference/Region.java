package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_REGION")
public class Region extends AbstractReference {

	@DBRef
	@Indexed
	private Country country;

	public Region(String id, String value, Country country) {
		super(id, value);
		this.country = country;
	}

	public Country getCountry() {
		return country;
	}
}
