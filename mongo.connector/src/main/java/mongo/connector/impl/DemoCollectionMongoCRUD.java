package mongo.connector.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import mongo.connector.crud.MongoCRUD;

public class DemoCollectionMongoCRUD {
	
	private final static String collectionName = "demo_collection";
	private MongoCRUD mongoCRUD;
	
	public DemoCollectionMongoCRUD(){
		this.mongoCRUD = new MongoCRUD();
	}
	
	public Set<DemoCollectionDocument> findObjects(String id, Date date, String content, String userName){
		Map<String, Object> map = DemoCollectionDocument.toMap(id, date, userName, content, null);
		Set<DemoCollectionDocument> set = mongoCRUD.query(collectionName, map, new DemoCollectionDocumentTransformer());
		return set;
	}
	
	public DemoCollectionDocument findObjectById(String id){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put(DemoCollectionDocument.idKey, id);
		DemoCollectionDocument doc = mongoCRUD.findOne(collectionName, DemoCollectionDocument.idKey, id, new DemoCollectionDocumentTransformer());
		return doc;
	}
	
	public void saveDemoCollectionDocument(String id, Date date, String content, String userName, Boolean isEdited){
		mongoCRUD.save(collectionName, DemoCollectionDocument.toMap(id, date, userName, content, isEdited));
	}
	
	public void saveDemoCollectionDocument(DemoCollectionDocument document){
		mongoCRUD.save(collectionName, document.toMap());
	}
}
