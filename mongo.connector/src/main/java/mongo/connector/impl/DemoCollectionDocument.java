package mongo.connector.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.mongodb.DBObject;

public class DemoCollectionDocument implements MongoDBDocument {

	public static final String idKey = "id";
	public static final String dateKey = "date";
	public static final String contentKey = "content";
	public static final String userNameKey = "userName";
	public static final String isEditedKey = "isEditedName";

	private String id;
	private Date date;
	private String content;
	private String userName;
	private Boolean isEdited;

	public DemoCollectionDocument(String id, Date date, String content,
			String userName, Boolean isEdited) {
		super();
		this.id = id;
		this.date = date;
		this.content = content;
		this.userName = userName;
		this.isEdited = isEdited;
	}

	public DemoCollectionDocument(Date date, String content, String userName,
			Boolean isEdited) {
		super();
		this.id = UUID.randomUUID().toString();
		this.date = date;
		this.content = content;
		this.userName = userName;
		this.isEdited = isEdited;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getIsEdited() {
		return isEdited;
	}

	public void setIsEdited(Boolean isEdited) {
		this.isEdited = isEdited;
	}

	public DemoCollectionDocument(DBObject dbObject) {
		super();
		if (dbObject != null) {
			this.id = (String) dbObject.get(idKey);
			this.date = (Date) dbObject.get(dateKey);
			this.content = (String) dbObject.get(contentKey);
			this.isEdited = (Boolean) dbObject.get(isEditedKey);
			this.userName = (String) dbObject.get(userNameKey);
		} else {
			this.id = null;
			this.date = null;
			this.content = null;
			this.userName = null;
			this.isEdited = null;
		}
	}

	public Map<String, Object> toMap() {
		Map<String, Object> objectAsMap = new LinkedHashMap<String, Object>();
		objectAsMap.put(idKey, id);
		objectAsMap.put(dateKey, date);
		objectAsMap.put(contentKey, content);
		objectAsMap.put(userNameKey, userName);
		objectAsMap.put(isEditedKey, isEdited);
		return objectAsMap;
	}

	public static Map<String, Object> toMap(String id, Date date,
			String userName, String content, Boolean isEdited) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (id != null)
			map.put(idKey, id);
		if (date != null)
			map.put(dateKey, date);
		if (content != null)
			map.put(contentKey, content);
		if (userName != null)
			map.put(userNameKey, userName);
		if (isEdited != null)
			map.put(isEditedKey, isEdited);
		return map;
	}
}
