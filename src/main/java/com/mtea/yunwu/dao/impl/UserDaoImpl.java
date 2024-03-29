package com.mtea.yunwu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mtea.yunwu.dao.UserDao;
import com.mtea.yunwu.dao.sql.WhereBuilder;
import com.mtea.yunwu.model.core.User;
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
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

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
		
		logger.debug("{}Dao - findPage() - 分页查询条件信息: {} ", getModelClass().getSimpleName(), criteria);
		
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
	
}