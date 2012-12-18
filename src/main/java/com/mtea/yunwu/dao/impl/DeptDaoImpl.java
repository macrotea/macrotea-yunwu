package com.mtea.yunwu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.DeptDao;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.Dept;
import com.mtea.yunwu.model.ext.SqlParamBean;
import com.mtea.yunwu.utils.Pager;

/**
 * 部门数据访问实现类
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-3-27 下午5:50:32
 * @note
 */
@Repository
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.DeptDao#findPage(int, com.mtea.yunwu.model.core.Dept)
	 */
	@Override
	public Pager<Dept> findPage(int page, final Dept criteriaDept) {
		return this.findPage(page, Pager.DEFAULT_PAGE_SIZE, criteriaDept);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.DeptDao#findPage(int, int, com.mtea.yunwu.model.core.Dept)
	 */
	@Override
	public Pager<Dept> findPage(int page, int pageSize, final Dept criteria) {
		
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
	public Class<Dept> getModelClass() {
		return Dept.class;
	}
	
}