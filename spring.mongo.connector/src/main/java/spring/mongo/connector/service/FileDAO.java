package spring.mongo.connector.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import spring.mongo.connector.domain.file.FileMeta;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@Repository
public class FileDAO {

	// private final static String FILE_META = "meta";
	// private final static String UPLOAD_DATE_META = "uploadDate";

	@Autowired
	GridFsOperations gridFsTemplate;

	public String storeMediaFile(InputStream is, String fileName, String cType,
			FileMeta extraMeta) throws IOException {
		/*
		 * DBObject fileMeta = new BasicDBObject(); ObjectMapper mapper = new
		 * ObjectMapper(); fileMeta.put(FILE_META,
		 * mapper.writeValueAsString(extraMeta));
		 */
		GridFSFile gridFsFile = gridFsTemplate.store(is, fileName, cType,
				extraMeta);
		return ((ObjectId) gridFsFile.getId()).toHexString();
	}

	public List<GridFSDBFile> getAllVideosForCompany(String userName) {
		List<GridFSDBFile> videoList = gridFsTemplate.find(new Query(Criteria
				.where("un").is(userName)));
		if (videoList != null)
			return videoList;
		else
			return Collections.emptyList();
	}

	public GridFSDBFile getVideoById(String id) {
		GridFSDBFile file = gridFsTemplate.findOne(new Query(Criteria.where(
				"_id").is(id)));
		return file;
	}

}
