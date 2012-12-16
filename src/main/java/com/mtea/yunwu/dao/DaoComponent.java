package com.mtea.yunwu.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.mtea.yunwu.dao.sql.SqlBuilder;
import com.mtea.yunwu.utils.ModelInspector;

/**
 * <pre>
 * 数据访问组件
 * 主要是数据访问核心组件的注入和获取
 * </pre>
 * @author macrotea@qq.com
 * @date 2012-12-17 上午12:18:01
 * @version 1.0
 * @note
 */
public abstract class DaoComponent<T> {
	
	public abstract Class<T> getModelClass();
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private ModelInspector modelInspector;
	
	private SqlBuilder sqlBuilder;

	private SimpleJdbcInsert insertActor;
	
	private ParameterizedBeanPropertyRowMapper<T> mapper;
	
	private String tableName;
	
	public DaoComponent() {
		super();
	}

	public void init() {
		jdbcTemplate=new JdbcTemplate(getDataSource());
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
		
		this.mapper = ParameterizedBeanPropertyRowMapper.newInstance(getModelClass());
		this.modelInspector = ModelInspector.forClazz(getModelClass());
		this.sqlBuilder = SqlBuilder.use(this.modelInspector);
		this.tableName = modelInspector.getTableName();
		this.insertActor = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName(tableName).usingColumns(modelInspector.getCommonColumns()).usingGeneratedKeyColumns(modelInspector.getPKColumn());
	}
	
	/* 组件获取 */
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		init();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected SimpleJdbcInsert getInsertActor() {
		return insertActor;
	}

	protected ModelInspector getModelInspector() {
		return modelInspector;
	}

	protected SqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	protected ParameterizedBeanPropertyRowMapper<T> getMapper() {
		return mapper;
	}

	protected String getTableName() {
		return tableName;
	}

	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

}
