package com.mtea.yunwu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.EmployeeDao;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.Employee;
import com.mtea.yunwu.model.ext.SqlParamBean;
import com.mtea.yunwu.utils.Pager;

/**
 * 员工数据访问实现类
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-3-27 下午5:50:32
 * @note
 */
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#findPage(int, com.mtea.yunwu.model.core.Employee)
	 */
	@Override
	public Pager<Employee> findPage(int page, final Employee criteriaEmployee) {
		return this.findPage(page, Pager.DEFAULT_PAGE_SIZE, criteriaEmployee);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#findPage(int, int, com.mtea.yunwu.model.core.Employee)
	 */
	@Override
	public Pager<Employee> findPage(int page, int pageSize, final Employee criteria) {
		
		logger.debug("{}Dao - findPage() - 分页查询条件信息: {} ", getModelClass().getSimpleName(), criteria);
		
		return super.findPageTemplate(page, pageSize, new WhereBuilder() {

			@Override
			public SqlParamBean buildSqlParamBean() {

				return new SqlParamBean(sql(), getParamList());
			}
		});
	}
	
	/*
	 * 覆盖的方法
	 *-------------------------------------------*/

	@Override
	public Class<Employee> getModelClass() {
		return Employee.class;
	}
	
}