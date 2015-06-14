package spring.mongo.connector.domain.blog;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.Tag;

import spring.mongo.connector.domain.Comment;
import spring.mongo.connector.domain.PersistentObject;
import spring.mongo.connector.domain.User;
import spring.mongo.connector.domain.reference.DocumentType;

@Document(collection = "BLOG_POST")
public class AbstractPost extends PersistentObject{

	@TextIndexed
	@Field(value = "cnt")
	private String content;
	
	@Indexed
	@Field(value = "u")
	private User user;
	
	@Field(value = "pd")
	@Indexed
	private Date postDate;
	
	@Field(value = "t")
	protected DocumentType type;

	@Field(value = "cs")
	@DBRef(lazy = true)
	private List<Comment> comments; 
	
	@Field(value = "tl")
	@DBRef(lazy = false)
	private List<Tag> tags;
	
	@PersistenceConstructor
	public AbstractPost(String id, String content, User user, Date postDate, List<Tag> tags) {
		super(id);
		this.content = content;
		this.user = user;
		this.postDate = postDate;
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public User getUser() {
		return user;
	}

	public Date getPostDate() {
		return postDate;
	}

	public DocumentType getType() {
		return type;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<Tag> getTags() {
		return tags;
	}
}
