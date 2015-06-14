package spring.mongo.connector.domain.blog;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import spring.mongo.connector.domain.Company;
import spring.mongo.connector.domain.User;
import spring.mongo.connector.domain.reference.DocumentType;

import com.mongodb.Tag;

//@Document(collection = "BLOG_POST")
public class CompanyPost extends AbstractPost {

	@Field(value="cmp")
	private Company company;
	
	public CompanyPost(String id, String content, User user, Date postDate,
			List<Tag> tags) {
		super(id, content, user, postDate, tags);
		this.type = DocumentType.COMPANY_POST;
	}
	
	public Company getCompany(){
		return this.company;
	}
}
