package spring.mongo.connector.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import spring.mongo.connector.domain.reference.Position;

@Document(collection = "Employee")
public class Employee extends PersistentObject {

	@DBRef
	@Field(value = "ou")
	private OrgUnit orgUnit;

	@DBRef
	@Field(value = "prsn")
	private User user;

	@Field(value = "pos")
	@DBRef
	private Position position;

	@Field(value = "cmp")
	@DBRef
	private Company company;

	@Field(value = "swd")
	private Date startWorkingDate;

	public Employee(String id, OrgUnit orgUnit, User user, Position position,
			Date startWorkingDate, Company company) {
		super(id);
		this.orgUnit = orgUnit;
		this.user = user;
		this.position = position;
		this.startWorkingDate = startWorkingDate;
		this.company = company;
	}

	public Company getCompany() {
		return this.company;
	}

	public OrgUnit getOrgUnit() {
		return orgUnit;
	}

	public User getUser() {
		return user;
	}

	public Position getPosition() {
		return position;
	}

	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

}
