package com.mtea.yunwu.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.mtea.yunwu.dao.BaseDao;
import com.mtea.yunwu.dao.DaoComponent;
import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.BaseModel;
import com.mtea.yunwu.model.ext.SqlParamBean;
import com.mtea.yunwu.utils.Pager;

/**
 * <pre>
 * 父级数据访问类
 * 主要是提供数据访问方法的封装
 * </pre>
 * @author macrotea@qq.com
 * @date 2012-12-12 下午9:16:31
 * @version 1.0
 * @note
 */
public abstract class BaseDaoImpl<T extends BaseModel> extends DaoComponent<T> implements BaseDao<T> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public BaseDaoImpl() {
		super();
	}
	
	/**
	 * 查找分页数据模板方法
	 * @author macrotea@qq.com
	 * @date 2012-12-12 下午9:44:45
	 * @param page
	 * @param pageSize
	 * @param whereBuilder
	 */
	public Pager<T> findPageTemplate(int page, int pageSize, WhereBuilder whereBuilder) {
		String countSql = super.getSqlBuilder().toCountAllSql();
		String querySql = super.getSqlBuilder().toFindAllSql();
		String limitSql = String.format(" LIMIT %s,%s", (page - 1) * pageSize, pageSize);
		
		/*
		 * Where条件SQL及其参数 
		 */
		SqlParamBean sqlParamBean = whereBuilder.buildSqlParamBean();
		String whereSql = sqlParamBean.getSql();
		List<Object> paramList = sqlParamBean.getParamList();
		Object[] args = paramList.toArray(new Object[paramList.size()]);
		
		//获得总行数
		long rowCount = this.getJdbcTemplate().queryForLong(countSql + whereSql , args);
		logger.debug("findPageTemplate() - rowCount : " + rowCount);
		
		//获得特定页数据
		List<T> userList = getJdbcTemplate().query(querySql + whereSql + limitSql, getMapper(), args);
		return new Pager<T>(userList, rowCount, page);
	}
	
	@Override
	public T save(T modelInstance) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(modelInstance);
        Number newId = getInsertActor().executeAndReturnKey(parameters);
        modelInstance.setId(newId.longValue());
        return modelInstance;
	}
	
	@Override
	public long delete(long id) {
		return this.getJdbcTemplate().update(getSqlBuilder().toDeleteByIdSql(),id);
	}
	
	@Override
	public long deleleAll() {
		return this.getJdbcTemplate().update(getSqlBuilder().toDeleteAllSql());
	}

	@Override
	public List<T> findAll() {
		return this.getJdbcTemplate().query(getSqlBuilder().toFindAllSql(),getMapper());
	}

	@Override
	public T findById(long id) {
		return this.getJdbcTemplate().queryForObject(getSqlBuilder().toFindByIdSql(), new Object[] { id }, getMapper());
	}

	@Override
	public Long countAll() {
		return this.getJdbcTemplate().queryForLong(getSqlBuilder().toCountAllSql());
	}
	
	@Override
	public long updateById(String sql, T databean) throws DaoException{
		return updateByNamedParams(sql,databean);
	}
	
	@Override
	public long updateById(T databean) throws DaoException{
		return updateById(getSqlBuilder().toUpdateByIdSql(),databean);
	}
	
	@Override
	public long updateByNamedParams(String namedSql, Object databean) throws DaoException{
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(databean);
		return this.getNamedParameterJdbcTemplate().update(namedSql,paramSource);
	}
	
}


/**

代码备份说明:
1.
//Bean中属性与列名映射,忽略大小写
SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
//插入且生成主键
Number newId = getInsertActor().executeAndReturnKey(parameters);
//更新对象中的Id值
user.setId(newId.longValue());
return user;

2.


**/