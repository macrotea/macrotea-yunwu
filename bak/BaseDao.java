package com.mtea.yunwu.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.mtea.yunwu.dao.sql.SqlBuilder;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.BaseModel;
import com.mtea.yunwu.model.ext.SqlParamBean;
import com.mtea.yunwu.utils.ModelInspector;
import com.mtea.yunwu.utils.Pager;

/**
 * @author macrotea@qq.com
 * @date 2012-12-12 下午9:16:31
 * @version 1.0
 * @note
 */
public abstract class BaseDao<T extends BaseModel> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public abstract void initDataSource(DataSource dataSource);
	
	public abstract Class<T> getModelClass();
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	private ModelInspector modelInspector;
	
	private SqlBuilder sqlBuilder;

	private SimpleJdbcInsert insertActor;
	
	private ParameterizedBeanPropertyRowMapper<T> mapper;
	
	private String tableName;
	
	public BaseDao() {
		super();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		init();
	}

	public void init() {
		jdbcTemplate=new JdbcTemplate(getDataSource());
		this.mapper = ParameterizedBeanPropertyRowMapper.newInstance(getModelClass());
		this.modelInspector = ModelInspector.forClazz(getModelClass());
		this.sqlBuilder = SqlBuilder.use(this.modelInspector);
		this.tableName = modelInspector.getTableName();
		this.insertActor = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName(modelInspector.getTableName()).usingColumns(modelInspector.getCommonColumns()).usingGeneratedKeyColumns(modelInspector.getPKColumn());
	}
	
	/* 组件获取 */

	public DataSource getDataSource() {
		return dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public SimpleJdbcInsert getInsertActor() {
		return insertActor;
	}

	public ModelInspector getModelInspector() {
		return modelInspector;
	}

	public SqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public ParameterizedBeanPropertyRowMapper<T> getMapper() {
		return mapper;
	}

	public String getTableName() {
		return tableName;
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
		String countSql = sqlBuilder.toCountAllSql();
		String querySql = sqlBuilder.toFindAllSql();
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