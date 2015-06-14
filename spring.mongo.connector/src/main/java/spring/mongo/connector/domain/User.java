package spring.mongo.connector.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "USER")
@CompoundIndexes({ @CompoundIndex(name = "user_ln_fn_idx", def = "{'lastName':1,'firstName':1}") })
public class User extends PersistentObject {

	@Indexed(unique = true)
	@Field(value="un")
	private String userName;
	@Field(value="ln")
	private String lastName;
	@Field(value="fn")
	private String firstName;
	@Field(value="mn")
	private String middleName;
	@Indexed(unique = true)
	@Field(value = "em")
	private String email;
	@Field(value = "bd")
	private Date birthDate;
	
	@Field(value="ur")
	private List<UserRole> userRoles;
	
	@Transient
	private short age;

	@PersistenceConstructor
	public User(String id, String userName, String email, String lastName,
			String firstName, String middleName, Date birthDate, List<UserRole> userRoles) {
		super(id);
		this.userName = userName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.firstName = firstName;
		this.email = email;
		this.birthDate = birthDate;
		this.age = -1;
		this.userRoles = userRoles;
	}

	public String getUserName() {
		return userName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
	public short getAge(){
		if (age<=0 && this.birthDate!=null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int curYear = cal.get(Calendar.YEAR);
			cal.setTime(this.birthDate);
			int userBYear = cal.get(Calendar.YEAR);
			age = (short) (curYear - userBYear);
		}
		return age;
	}
	
	public void addRole(String role){
		if (this.userRoles == null){
			
		}
	}

	@Override
	public String toString() {
		return "[" + this.id + ", " + this.userName + ", " + email + ", "
				+ this.lastName + ", " + this.firstName + ", "
				+ this.middleName + ", "+ this.birthDate +"]";
	}
}
