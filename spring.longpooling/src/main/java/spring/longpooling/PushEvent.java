package spring.longpooling;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class PushEvent {
	private Date eventDate;
	private String someString;
	
	public PushEvent(){
		this.eventDate = new Date();
		this.someString = RandomStringUtils.randomAlphabetic(20);
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getSomeString() {
		return someString;
	}

	public void setSomeString(String someString) {
		this.someString = someString;
	}
}
