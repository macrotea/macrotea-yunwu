package com.mtea.yunwu.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mtea.yunwu.dao.extractor.UserResultSetExtractor;
import com.mtea.yunwu.model.core.User;

/**
 * User表行映射
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-3-27 下午5:41:38
 * @note
 */
public final class UserRowMapper implements RowMapper<User> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserResultSetExtractor extractor = new UserResultSetExtractor();
		return extractor.extractData(rs);
	}


}