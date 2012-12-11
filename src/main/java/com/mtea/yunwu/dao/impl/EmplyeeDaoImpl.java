package com.mtea.yunwu.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.EmployeeDao;
import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.dao.sql.SqlBuilder;
import com.mtea.yunwu.model.core.Employee;
import com.mtea.yunwu.model.ext.SqlArgsBean;
import com.mtea.yunwu.utils.ModelInspector;

/**
 * 员工数据访问实现类
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-3-27 下午5:50:32
 * @note
 */
@Repository
public class EmplyeeDaoImpl implements EmployeeDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ModelInspector modelInspector;
	
	private SqlBuilder sqlBuilder;

	private SimpleJdbcInsert insertActor;
	
	private ParameterizedBeanPropertyRowMapper<Employee> mapper;
	
	
	@PostConstruct
	public void init() {
		this.mapper = ParameterizedBeanPropertyRowMapper.newInstance(Employee.class);
		this.modelInspector = ModelInspector.forClazz(Employee.class);
		this.sqlBuilder = SqlBuilder.use(this.modelInspector);
		
		DataSource dataSource =jdbcTemplate.getDataSource();
        //设定表明,插入列,自动生成Id的列名
        this.insertActor = new SimpleJdbcInsert(dataSource)
        	.withTableName(modelInspector.getTableName())
        	.usingColumns(modelInspector.getCommonColumns())
        	.usingGeneratedKeyColumns(modelInspector.getPKColumn());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#save(com.mtea.yunwu.model.Employee)
	 */
	public Employee save(Employee employee) {
		//Bean中属性与列名映射,忽略大小写
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(employee);
        //插入且生成主键
        Number newId = insertActor.executeAndReturnKey(parameters);
        //更新对象中的Id值
        employee.setId(newId.longValue());
        return employee;
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#delete(int)
	 */
	public long delete(long id) {
		return this.jdbcTemplate.update(sqlBuilder.toDeleteByIdSql(),id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#deleleAll()
	 */
	public long deleleAll() {
		return this.jdbcTemplate.update(sqlBuilder.toDeleteAllSql());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#findAll()
	 */
	public List<Employee> findAll() {
		return this.jdbcTemplate.query(sqlBuilder.toFindAllSql(),mapper);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#updateEmployeeById(com.mtea.yunwu.model.Employee)
	 */
	public long updateById(Employee employee) throws DaoException{
		SqlArgsBean b=null;
		try {
			b = sqlBuilder.createUpdateSqlArgsBean(employee);
		} catch (Exception e) {
			String message = String.format("根据%s实例获得更新的SqlArgsBean出错", modelInspector.getModelClazz().getSimpleName());
			logger.error(message, e);
			throw new DaoException(message);
		}
		// NOTICE lqy-sprint5/2012-12-1 toArray这步很关键
		return this.jdbcTemplate.update(b.getSql(),b.getArgs().toArray());
		
		/*
		String sql =sqlBuilder.toUpdateByIdSql();
		logger.debug("update sql : "+ sql);
		return this.jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));
		*/
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#findById(long)
	 */
	public Employee findById(long id) {
		return this.jdbcTemplate.queryForObject(sqlBuilder.toFindByIdSql(), new Object[] { id }, mapper);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.EmployeeDao#countAll()
	 */
	public Long countAll() {
		return this.jdbcTemplate.queryForLong(sqlBuilder.toCountAllSql());
	}
  

}