package com.kaipin.oss.repository.dao.company.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.company.CompanyDocument;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.repository.dao.company.CompanyInfoBaseDao;
import com.kaipin.oss.repository.mybatis.dao.IBasePageSql;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;

@SuppressWarnings("unchecked")
@Repository
public class CompanyInfoBaseDaoImpl extends MybatisBaseDAO<CompanyInfoCount, String>implements CompanyInfoBaseDao{
	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.company.CompanyInfoCount";
	
	public IGenericPage<CompanyInfoCount> getPage(Map<String,Object> param,int pageNo,int pageSize){
		
		return this.findPageBy( new IBasePageSql(){

			@Override
			public String getPageListSqlName() {
				return "getCompanyList";
			}

			@Override
			public String getPageCountSqlName() {
			 
				return "getCompanyListCount";
			}}, param, pageNo, pageSize, null, null);
		
	}
	
	
	@Override
	public String getDefaultSqlNamespace() {
		return NAMESPACE_INFO;
	}
	
	
	@Override
	public IGenericPage<CompanyInfoCount> getCompanyDocList(Map<String,Object> param, int pageNo, int pageSize) {
		IBasePageSql sql = new IBasePageSql() {
			
			@Override
			public String getPageListSqlName() {
				return "getCompanyDocumentList";
			}
			
			@Override
			public String getPageCountSqlName() {
				return "getCompanyDocumentCount";
			}
		};
		return this.findPageBy(sql, param, pageNo, pageSize, "d.create_time", "desc");
	}


	@Override
	public List<CompanyDocument> getComanyDoc(String companyId) {
		return this.selectList("getCompanyDocument", companyId);
	}


	
	@Override
	public List<Map<String, Object>> getRegeditCompanyCount(Map<String, Object> map) {
		return this.selectList("getRegeditCompanyCount", map);
	}

	@Override
	public List<Map<String, Object>> getRegeditStudentCount(Map<String, Object> map) {
		return this.selectList("getRegeditStudentCount", map);
	}
	
	@Override
	public List<Map<String, Object>> getRegeditSchCount(Map<String, Object> map) {
		return this.selectList("getRegeditSchCount",map);
	}


	@Override
	public List<Map<String, Object>> getRegeditAllCompanyCount() {
		return this.selectList("getRegeditAllCompanyCount");
	}


	@Override
	public List<Map<String, Object>> getRegeditAllStudentCount() {
		return this.selectList("getRegeditAllStudentCount");
	}


	@Override
	public List<Map<String, Object>> getPositionCount() {
		return this.selectList("getPositionCount");
	}


	@Override
	public List<Map<String, Object>> getResumeCount() {
		return this.selectList("getResumeCount");
	}


	@Override
	public List<Map<String, Object>> getPositionDeliveryCount() {
		return this.selectList("getPositionDeliveryCount");
	}


	@Override
	public List<Map<String, Object>> getResumeDeliveryCount() {
		return this.selectList("getResumeDeliveryCount");
	}


	@Override
	public List<Map<String, Object>> getXJHCount(String companyId) {
		return this.selectList("getXJHCount",companyId);
	}


	@Override
	public List<Map<String, Object>> getOfferCount(String companyId) {
		return this.selectList("getOfferCount",companyId);
	}


	@Override
	public List<Map<String, Object>> checkOfferConfig(String companyId) {
		return this.selectList("checkOfferConfig",companyId);
	}


	

}
