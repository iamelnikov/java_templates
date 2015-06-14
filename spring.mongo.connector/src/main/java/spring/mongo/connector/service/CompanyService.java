package spring.mongo.connector.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mongo.connector.domain.Company;
import spring.mongo.connector.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public CompanyService(){
		super();
	}
	
	public String addNewCompany(Company company){
		String uuid = UUID.randomUUID().toString();
		company.setId(uuid);
		companyRepository.save(company);
		return uuid;
	}
	
	public Company getCompanyById(String id){
		return companyRepository.findOne(id);
	}
	
	public List<Company> findCompanyByName(String name){
		return companyRepository.findCompanyByFullNameOrShortName(name, name);
	}
	
}
