package spring.mongo.connector.domain.blog;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import spring.mongo.connector.domain.User;
import spring.mongo.connector.domain.reference.DocumentType;

import com.mongodb.Tag;

//@Document(collection = "BLOG_POST")
public class OrgUnitPost extends AbstractPost{

	public OrgUnitPost(String id, String content, User user, Date postDate, List<Tag> tags) {
		super(id, content, user, postDate, tags);
		this.type = DocumentType.ORG_UNIT_POST; 
	}
	
}
