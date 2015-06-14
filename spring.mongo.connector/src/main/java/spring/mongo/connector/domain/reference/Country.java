package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_COUNTRY")
public class Country extends AbstractReference {
 
	@Indexed(unique = true)
	private String isoCode;
	
	public Country(String id, String value) {
		super(id, value);
	}
}
