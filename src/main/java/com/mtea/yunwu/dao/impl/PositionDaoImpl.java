package com.mtea.yunwu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.PositionDao;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.Position;
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
public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.PositionDao#findPage(int, com.mtea.yunwu.model.core.Position)
	 */
	@Override
	public Pager<Position> findPage(int page, final Position criteriaPosition) {
		return this.findPage(page, Pager.DEFAULT_PAGE_SIZE, criteriaPosition);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.PositionDao#findPage(int, int, com.mtea.yunwu.model.core.Position)
	 */
	@Override
	public Pager<Position> findPage(int page, int pageSize, final Position criteria) {
		
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
	public Class<Position> getModelClass() {
		return Position.class;
	}
	
}