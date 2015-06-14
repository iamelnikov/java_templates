package spring.mongo.connector.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import spring.mongo.connector.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{
	
	public List<Employee> findEmployeeByCompanyId(String companyId);
	public List<Employee> findEmployeeByCompanyFullName(String companyFullName);
	public List<Employee> findEmployeeByOrgUnitId(String orgUnitId);
}
