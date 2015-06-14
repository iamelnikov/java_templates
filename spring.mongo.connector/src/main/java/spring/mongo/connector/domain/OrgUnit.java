package spring.mongo.connector.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ORGANIZATION_UNIT")
public class OrgUnit extends PersistentObject{

	@Field(value = "oun")
	private String name;
	@DBRef
	@Field(value = "head")
	private User head;
	
	@DBRef 
	private Company company;
	
	@DBRef(lazy = true)
	private OrgUnit parentOrgUnit;
	
	@DBRef(lazy = true)
	private List<OrgUnit> childOrgUnits;
	
	@DBRef(lazy = true)
	private List<Employee> employees;

	public OrgUnit(String id, String name, User head, Company company, OrgUnit parentOrgUnit,
			List<OrgUnit> childOrgUnits) {
		super(id);
		this.name = name;
		this.head = head;
		this.company = company;
		this.parentOrgUnit = parentOrgUnit;
		this.childOrgUnits = childOrgUnits;
	}

	public String getName() {
		return name;
	}

	public User getHead() {
		return head;
	}

	public OrgUnit getParentOrgUnit() {
		return parentOrgUnit;
	}

	public List<OrgUnit> getChildOrgUnits() {
		return childOrgUnits;
	}
	
}
