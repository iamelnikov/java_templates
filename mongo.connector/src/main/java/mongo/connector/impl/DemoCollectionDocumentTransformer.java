package mongo.connector.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DemoCollectionDocumentTransformer implements
		DocumentTransformer<DemoCollectionDocument> {

	public DemoCollectionDocument transform(DBObject obj) {
		return new DemoCollectionDocument(obj);
	}

	public DBObject reverseTransform(DemoCollectionDocument obj) {
		if (obj!=null)
			return new BasicDBObject(obj.toMap());
		else
			return new BasicDBObject();
	}

}
