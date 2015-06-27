package spring.mongo.connector.domain.file;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FileMeta implements Serializable {

	private static final long serialVersionUID = 97672689779294976L;

	@JsonProperty("un")
	@Field(value = "un")
	@Indexed
	private String userName;

	@JsonProperty("fn")
	@Field("fn")
	@Indexed
	private String fileName;

	public FileMeta() {
	}

	public FileMeta(String userName, String fileName) {
		super();
		this.userName = userName;
		this.fileName = fileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
