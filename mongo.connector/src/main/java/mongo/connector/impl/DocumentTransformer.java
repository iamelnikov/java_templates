package mongo.connector.impl;

import com.mongodb.DBObject;

public interface DocumentTransformer<T> {

	public T transform(DBObject obj);
	public DBObject reverseTransform(T obj);
}
