package com.mtea.yunwu.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mtea.yunwu.model.core.User;

/**
 * User表数据抽取者
 * @author macrotea@qq.com
 * @version v1.0
 * @date 2012-3-27 下午5:39:30
 * @note
 */
public class UserResultSetExtractor implements ResultSetExtractor<User>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Long id=rs.getLong("id");
		Date addTime=rs.getDate("addTime");
		Date editTime=rs.getDate("editTime");
		String remark=rs.getString("remark");
		
		String username=rs.getString("username");
		String password=rs.getString("password");
		String email=rs.getString("email");
		Boolean isEnable=rs.getBoolean("isEnable");
		Boolean isAdmin=rs.getBoolean("isAdmin");
		
		User u =new User();
		u.setId(id);
		u.setAddTime(addTime);
		u.setEditTime(editTime);
		u.setRemark(remark);
		
		u.setUsername(username);
		u.setPassword(password);
		u.setEmail(email);
		u.setIsEnable(isEnable);
		u.setIsAdmin(isAdmin);
		
		return u;
	}

	
	
}