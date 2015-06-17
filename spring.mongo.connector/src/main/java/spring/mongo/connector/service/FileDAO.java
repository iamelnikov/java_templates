package spring.mongo.connector.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import spring.mongo.connector.domain.file.FileWrapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class FileDAO {
	
	 private final static String FILE_META = "meta"; 
	 private final static String UPLOAD_DATE_META = "uploadDate";
	 
	 @Autowired 
	 GridFsOperations gridFsTemplate;
	 
	 public String storeMediaFile(FileWrapper wrapper) throws IOException{
		 DBObject fileMeta = new BasicDBObject();
		 ObjectId objectId = new ObjectId();
		 fileMeta.put("_id", objectId);
		 fileMeta.put(FILE_META, wrapper);
		 fileMeta.put(UPLOAD_DATE_META, new Date());
		 gridFsTemplate.store(wrapper.getMultipartFile().getInputStream(), fileMeta);
		 return objectId.toHexString();
	 }
	 
	 public List<GridFSDBFile> getAllVideosForCompany(String userName){
		 List<GridFSDBFile> videoList = gridFsTemplate.find(new Query(Criteria.where("un").is(userName)));
		 if (videoList!=null)
			 return videoList;
		 else
			 return Collections.emptyList();
	 }
	 
}
