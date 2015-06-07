package mongo.connector.impl;

import java.util.Map;

public interface MongoDBDocument {
	
	public Map<String, Object> toMap();
}
