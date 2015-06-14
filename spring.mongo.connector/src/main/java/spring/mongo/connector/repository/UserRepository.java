package spring.mongo.connector.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import spring.mongo.connector.domain.User;

public interface UserRepository extends CrudRepository<User, String>{

	public User findUserByIdAllIgnoreCase(String userId, Sort sort);
	public User findUserByUserNameAllIgnoreCase(String userName, Sort sort);
	public User findUserByEmailAllIgnoreCase(String email, Sort sort);
	public List<User> findUserByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName, Sort sort);
}
