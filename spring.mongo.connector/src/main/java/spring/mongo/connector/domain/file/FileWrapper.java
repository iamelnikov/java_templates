package spring.mongo.connector.domain.file;

import java.util.Date;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileWrapper {
	
	@JsonProperty("un")
	@Field(value = "un")
	private String userName;
	
	@JsonProperty("fn")
	@Field("fn")
	private String fileName;

	@JsonProperty("ud")
	@Field("ud")
	private Date uploadDate;
	
	@Transient
	private MultipartFile multipartFile;
	
	public FileWrapper() {
		super();
	}
	
	@PersistenceConstructor
	public FileWrapper(String userName, String fileName, Date uploadDate) {
		super();
		this.userName = userName;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
	}
	
	public MultipartFile getMultipartFile(){
		return multipartFile;
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

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	public boolean isInputStreamNullOrEmpty(){
		return ((this.multipartFile==null) || (this.multipartFile.isEmpty()));
	}
}
