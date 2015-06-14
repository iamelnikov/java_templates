package spring.mongo.connector.domain.reference;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "REF_CITY")
public class City extends AbstractReference {
	
	@DBRef
	@Indexed
	private Region region;

	public City(String id, String value, Region region) {
		super(id, value);
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}

}
