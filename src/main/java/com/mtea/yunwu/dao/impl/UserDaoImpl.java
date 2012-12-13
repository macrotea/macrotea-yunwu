package com.mtea.yunwu.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.BaseDao;
import com.mtea.yunwu.dao.UserDao;
import com.mtea.yunwu.dao.exception.DaoException;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.User;
import com.mtea.yunwu.model.ext.SqlArgsBean;
import com.mtea.yunwu.model.ext.SqlParamBean;
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

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#save(com.mtea.yunwu.model.User)
	 */
	public User save(User user) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        Number newId = getInsertActor().executeAndReturnKey(parameters);
        user.setId(newId.longValue());
        return user;
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#delete(int)
	 */
	public long delete(long id) {
		return this.getJdbcTemplate().update(getSqlBuilder().toDeleteByIdSql(),id);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#deleleAll()
	 */
	public long deleleAll() {
		return this.getJdbcTemplate().update(getSqlBuilder().toDeleteAllSql());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findAll()
	 */
	public List<User> findAll() {
		return this.getJdbcTemplate().query(getSqlBuilder().toFindAllSql(),getMapper());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#updateUserById(com.mtea.yunwu.model.User)
	 */
	public long updateById(User user) throws DaoException{
		SqlArgsBean b=null;
		try {
			b = getSqlBuilder().createUpdateSqlArgsBean(user);
		} catch (Exception e) {
			String message = String.format("根据%s实例获得更新的SqlArgsBean出错", getModelInspector().getModelClazz().getSimpleName());
			logger.error(message, e);
			throw new DaoException(message);
		}
		// NOTICE lqy/2012-12-1 toArray这步很关键
		return this.getJdbcTemplate().update(b.getSql(),b.getArgs().toArray());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findById(long)
	 */
	public User findById(long id) {
		return this.getJdbcTemplate().queryForObject(getSqlBuilder().toFindByIdSql(), new Object[] { id }, getMapper());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#countAll()
	 */
	public Long countAll() {
		return this.getJdbcTemplate().queryForLong(getSqlBuilder().toCountAllSql());
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findPage(int, com.mtea.yunwu.model.core.User)
	 */
	@Override
	public Pager<User> findPage(int page, final User criteriaUser) {
		return this.findPage(page, Pager.DEFAULT_PAGE_SIZE, criteriaUser);
	}

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.dao.UserDao#findPage(int, int, com.mtea.yunwu.model.core.User)
	 */
	@Override
	public Pager<User> findPage(int page, int pageSize, final User criteria) {
		return super.findPageTemplate(page, pageSize, new WhereBuilder() {

			@Override
			public SqlParamBean buildSqlParamBean() {

				if (criteria != null) {
					// 用户名
					String username = criteria.getUsername();
					if (notBlank(username)) {
						sqlAndLike("username");
						addParamLike(username);
					}

					// 邮箱
					String email = criteria.getEmail();
					if (notBlank(email)) {
						sqlAndLike("email");
						addParamLike(email);
					}

					// 是否启用
					boolean isEnable = criteria.isEnable();
					sqlAndEquals("enable");
					addParam(isEnable ? 1 : 0);
				}

				return new SqlParamBean(sql(), getParamList());
			}
		});
	}
	
	/*
	 * 覆盖的方法
	 *-------------------------------------------*/

	@Override
	public Class<User> getModelClass() {
		return User.class;
	}
	
	@Override
	@Autowired
	public void initDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}


	
}