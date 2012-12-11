package com.mtea.yunwu.gen.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mtea.yunwu.model.core.BaseModel;

/**
 * 用户表
 * 
 * @author macrotea@qq.com
 * @date 2012-11-29 下午9:13:13
 * @version 1.0
 * @note
 */
@Entity
@Table(name="tb_user")
public class User extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String email;

	private boolean admin;

	private boolean enable;

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
