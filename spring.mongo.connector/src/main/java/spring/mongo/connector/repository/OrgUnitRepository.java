package spring.mongo.connector.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import spring.mongo.connector.domain.OrgUnit;

public interface OrgUnitRepository extends CrudRepository<OrgUnit, String>{
	public OrgUnit findById(String id);
	public List<OrgUnit> findByCompanyId(String companyId);
	public List<OrgUnit> findByCompanyIdAndName(String companyId, String name);
	public List<OrgUnit> findByParentOrgUnitId(String parentOrgUnitId);
}
