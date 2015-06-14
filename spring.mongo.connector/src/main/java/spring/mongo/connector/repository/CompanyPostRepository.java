package spring.mongo.connector.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import spring.mongo.connector.domain.blog.CompanyPost;

public interface CompanyPostRepository /*extends CrudRepository<CompanyPost, String>*/ {

	public List<CompanyPost> findByCompanyIdAndPostDateGreaterThan(
			String companyId, Date postDate);

	public List<CompanyPost> findByCompanyId(String companyId);
}
