package spring.mongo.connector.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.mongo.connector.domain.Company;
import spring.mongo.connector.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/spring.mongo/v1/{companyId}", method = RequestMethod.GET)
	public Company getCompany(@PathVariable("companyId") String companyId){
		return companyService.getCompanyById(companyId); 
	}
	
	@RequestMapping(value = "/spring.mongo/v1/findcompanies", method = RequestMethod.GET)
	public List<Company> findCompanies(@RequestParam("n") String companyName){
		return companyService.findCompanyByName(companyName);
	}
	
	@RequestMapping(value = "/spring.mongo/v1/createcompany", method = RequestMethod.POST
				/*consumes = {"application/json;charset=UTF-8"}, produces={"application/json;charset=UTF-8"}*/
				/*headers = {"Accept: application/json", "Content-type: application/json"}*/)
	@ResponseBody
	public ResponseEntity<String> createCompany(@RequestBody Company company){
		System.out.println("REceice Reuest");
		String id = companyService.addNewCompany(company);
		System.out.println(id);
		ResponseEntity<String> re = new ResponseEntity<String>(id, HttpStatus.OK);
		return re;
	}
	
	@ExceptionHandler(Exception.class)
	public void ex(Exception e){
		e.printStackTrace();
	}
}
