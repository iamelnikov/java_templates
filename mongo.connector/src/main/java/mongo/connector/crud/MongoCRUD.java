package mongo.connector.crud;

import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import mongo.connector.ConnectionFactory;
import mongo.connector.impl.DocumentTransformer;
import mongo.connector.impl.SingleConnectionFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class MongoCRUD {

	private ConnectionFactory connectionFactory = new SingleConnectionFactory(
			"localhost", 27017, "demo_mongo_db");

	public DBCollection getDBCollection(String collectionName)
			throws MongoException {
		try {
			DB db = this.connectionFactory.getConnection();
			DBCollection dbCollection = db.getCollection(collectionName);
			return dbCollection;
		} catch (UnknownHostException uhe) {
			throw new MongoException("Could not resolve MongoDB host", uhe);
		}

	}

	public <T> Set<T> query(String collectionName,
			Map<String, Object> options, DocumentTransformer<T> dt) throws MongoException {
		DBCollection dbCollection = getDBCollection(collectionName);
		BasicDBObject dbObject = new BasicDBObject(options);
		DBCursor cursor = dbCollection.find(dbObject);
		Set<T> dbObjectSet = new LinkedHashSet<T>();
		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			dbObjectSet.add(dt.transform(dbObj));
		}
		return dbObjectSet;
	}
	
	public <T> T findOne(String collectionName, String key, Object value, DocumentTransformer<T> dt) throws MongoException {
		DBCollection dbCollection = getDBCollection(collectionName);
		BasicDBObject dbObject = new BasicDBObject(key, value);
		DBObject object = dbCollection.findOne(dbObject);
		return dt.transform(object);
	}

	public void save(String collectionName, Map<String, Object> map)
			throws MongoException {
		DBObject document = new BasicDBObject(map);
		DBCollection dbCollection = getDBCollection(collectionName);
		dbCollection.insert(document);
	}

	public void update(String collectionName, String ObjectKeyName,
			String ObjectValue, Map<String, Object> newOps)
			throws MongoException {
		DBCollection dbCollection = getDBCollection(collectionName);
		DBObject dbObject = new BasicDBObject(ObjectKeyName, ObjectValue);
		DBObject updObj = new BasicDBObject(newOps);

		DBObject o = new BasicDBObject("$set", updObj);

		dbCollection.update(dbObject, o);
	}

}
