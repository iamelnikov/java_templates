package spring.mongo.connector.domain;

import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import spring.mongo.connector.domain.reference.Industry;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "COMPANY")
public class Company extends PersistentObject {

	@JsonProperty(value="fn")
	@Field(value = "fn")
	@TextIndexed
	private String fullName;
	
	@JsonProperty(value="sn")
	@Field(value = "sn")
	@TextIndexed
	private String shortName;
	
	@JsonProperty(value = "frn")
	@Field(value = "frn")
	@TextIndexed
	private String friendyName;
	
	
	@JsonProperty(value = "la")
	@Field(value = "la")
	private Location legalAddress;
	
	@JsonProperty(value = "aa")
	@Field(value = "aa")
	private Location actualAddress;
	
	@JsonProperty(value = "pl")
	@Field(value = "pl")
	private List<Phone> phoneList; 
	
	@JsonProperty(value = "inn")
	@Field(value = "inn")
	@Indexed(unique = true)
	private String inn;
	
	@JsonProperty(value="hou")
	@DBRef
	@Field(value = "hou")
	private OrgUnit headOrgUnit;
	
	@JsonProperty(value = "ws")
	@Field(value = "ws")
	private String website;
	
	@JsonProperty(value = "ids")
	@Field(value="ids")
	@DBRef
	private Industry industry;

	public Company() {
		super(null);
	}
	
	@PersistenceConstructor
	public Company(String id, String friendlyName, String fullName, String shortName,
			Location legalAddress, Location actualAddress,
			List<Phone> phoneList, OrgUnit headOrgUnit, String website, Industry industry) {
		super(id);
		this.fullName = fullName;
		this.friendyName = friendlyName;
		this.shortName = shortName;
		this.legalAddress = legalAddress;
		this.actualAddress = actualAddress;
		this.phoneList = phoneList;
		this.headOrgUnit = headOrgUnit;
		this.industry = industry;
		this.website = website;
	}
	
	public Industry getIndustry(){
		return this.industry;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFriendyName() {
		return friendyName;
	}

	public Location getLegalAddress() {
		return legalAddress;
	}

	public Location getActualAddress() {
		return actualAddress;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public String getInn() {
		return inn;
	}

	public OrgUnit getHeadOrgUnit() {
		return headOrgUnit;
	}

	public String getWebsite() {
		return website;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setFriendyName(String friendyName) {
		this.friendyName = friendyName;
	}

	public void setLegalAddress(Location legalAddress) {
		this.legalAddress = legalAddress;
	}

	public void setActualAddress(Location actualAddress) {
		this.actualAddress = actualAddress;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public void setHeadOrgUnit(OrgUnit headOrgUnit) {
		this.headOrgUnit = headOrgUnit;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	} 
}
