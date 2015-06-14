package spring.mongo.connector.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import spring.mongo.connector.domain.blog.AbstractPost;

@Document(collection = "COMMENT")
public class Comment extends PersistentObject {

	@Field(value = "u")
	@DBRef
	private User user;

	@Field(value = "doc")
	@DBRef
	private AbstractPost document;
	
	@Field(value = "cnt")
	private String content;

	@Field(value = "cd")
	private String commentDate;
	
	@Field(value = "l")
	private long likes;
	
	@Field(value = "d")
	private long dislikes;
	
	@PersistenceConstructor
	public Comment(String id, User user, String content, String commentDate,
			long likes, long dislikes, AbstractPost document) {
		super(id);
		this.user = user;
		this.content = content;
		this.commentDate = commentDate;
		this.likes = likes;
		this.dislikes = dislikes;
		this.document = document;
	}
	
	public AbstractPost getDocument(){
		return this.document;
	}

	public User getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public long getLikes() {
		return likes;
	}

	public long getDislikes() {
		return dislikes;
	}
}
