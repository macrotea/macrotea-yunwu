package com.mtea.yunwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.BaseDao;
import com.mtea.yunwu.dao.UserDao;
import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.dao.sql.SqlBuilder;
import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.model.ext.SqlArgsBean;
import com.mtea.yunwu.utils.ModelInspector;
import com.mtea.yunwu.utils.Pager;

/**
 * 用户数据访问实现类
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-3-27 下午5:50:32
 * @note
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ModelInspector modelInspector;
	
	private SqlBuilder sqlBuilder;

	private SimpleJdbcInsert insertActor;
	
	private ParameterizedBeanPropertyRowMapper<User> mapper;
	
	private String tableName;
	
	@PostConstruct
	public void init() {
		this.mapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		this.modelInspector = ModelInspector.forClazz(User.class);
		this.sqlBuilder = SqlBuilder.use(this.modelInspector);
		this.tableName = modelInspector.getTableName();
		
		DataSource dataSource =jdbcTemplate.getDataSource();
        //设定表名,插入列,自动生成Id的列名
        this.insertActor = new SimpleJdbcInsert(dataSource)
        	.withTableName(modelInspector.getTableName())
        	.usingColumns(modelInspector.getCommonColumns())
        	.usingGeneratedKeyColumns(modelInspector.getPKColumn());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#save(com.mtea.yunwu.model.User)
	 */
	public User save(User user) {
		//Bean中属性与列名映射,忽略大小写
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        //插入且生成主键
        Number newId = insertActor.executeAndReturnKey(parameters);
        //更新对象中的Id值
        user.setId(newId.longValue());
        return user;
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#delete(int)
	 */
	public long delete(long id) {
		return this.jdbcTemplate.update(sqlBuilder.toDeleteByIdSql(),id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#deleleAll()
	 */
	public long deleleAll() {
		return this.jdbcTemplate.update(sqlBuilder.toDeleteAllSql());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findAll()
	 */
	public List<User> findAll() {
		return this.jdbcTemplate.query(sqlBuilder.toFindAllSql(),mapper);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#updateUserById(com.mtea.yunwu.model.User)
	 */
	public long updateById(User user) throws DaoException{
		SqlArgsBean b=null;
		try {
			b = sqlBuilder.createUpdateSqlArgsBean(user);
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
		return this.jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user));
		*/
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findById(long)
	 */
	public User findById(long id) {
		return this.jdbcTemplate.queryForObject(sqlBuilder.toFindByIdSql(), new Object[] { id }, mapper);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#countAll()
	 */
	public Long countAll() {
		return this.jdbcTemplate.queryForLong(sqlBuilder.toCountAllSql());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findPage(int, com.mtea.yunwu.model.core.User)
	 */
	@Override
	public Pager<User> findPage(int page, final User user) {
		
		super.findPage(page,new WhereBuilder() {
			
			@Override
			public SqlParamBean buildSqlParamBean() {
				StringBuilder whereBuilder =new StringBuilder();
				List<Object> paramList =new ArrayList<Object>();
				String username=user.getUsername();
				if(StringUtils.isNotBlank(username)){
					whereBuilder.append(" AND username LIKE ?");
					paramList.add("%" + username + "%");
				}
				
				String email=user.getEmail();
				if(StringUtils.isNotBlank(email)){
					whereBuilder.append(" AND email LIKE ?");
					paramList.add("%" + email + "%");
				}
				
				boolean isEnable=user.isEnable();
				whereBuilder.append(" AND enable = ?");
				paramList.add(isEnable ? 1 : 0);
				
				return new SqlParamBean();
					
			}
		});
		
		String countSql = String.format("SELECT COUNT(*) FROM %s WHERE 1=1", tableName);
		String querySql = String.format("SELECT * FROM %s WHERE 1=1", tableName);
		
		StringBuilder whereBuilder =new StringBuilder();
		List<Object> paramList =new ArrayList<Object>();
		
		String username=user.getUsername();
		if(StringUtils.isNotBlank(username)){
			whereBuilder.append(" AND username LIKE ?");
			paramList.add("%" + username + "%");
		}
		
		String email=user.getEmail();
		if(StringUtils.isNotBlank(email)){
			whereBuilder.append(" AND email LIKE ?");
			paramList.add("%" + email + "%");
		}
		
		boolean isEnable=user.isEnable();
		whereBuilder.append(" AND enable = ?");
		paramList.add(isEnable ? 1 : 0);
		
		String whereSql = whereBuilder.toString();
		Object[] args = paramList.toArray(new Object[paramList.size()]);
		
		long rowCount = this.jdbcTemplate.queryForLong(countSql + whereSql , args);
		logger.debug("findPage() - rowCount : " + rowCount);
		
		String limitSql = String.format(" LIMIT %s,%s", (page - 1) * Pager.DEFAULT_PAGE_SIZE, page * Pager.DEFAULT_PAGE_SIZE);
		List<User> userList = this.jdbcTemplate.query(querySql + whereSql + limitSql, mapper, args);
		return new Pager<User>(userList, rowCount, page);
	}

}