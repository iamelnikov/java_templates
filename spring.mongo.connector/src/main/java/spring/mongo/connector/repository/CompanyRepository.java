package spring.mongo.connector.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import spring.mongo.connector.domain.Company;

public interface CompanyRepository extends MongoRepository<Company, String>{
	public List<Company> findCompanyByFullNameOrShortName(String fullName, String shortName);
	public Company findCompanyByInn(String inn);
}
