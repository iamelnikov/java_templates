package spring.mongo.connector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyViewController {
	
	@RequestMapping(value = "/spring.mongo/v1/createcompany", method = RequestMethod.GET)
	public String getAddNewCompanyView(){
		return "/static/addNewCompany.html";
	}
}
