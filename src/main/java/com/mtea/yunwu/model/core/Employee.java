package com.mtea.yunwu.model.core;

import java.io.Serializable;

/**
 * 员工表
 * 
 * @author macrotea@qq.com
 * @date 2012-11-29 下午9:15:59
 * @version 1.0
 * @note
 */
public class Employee extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String realName;

	private Integer age;

	private Integer gender;

	private String mobilePhoneNumber;

	private String officePhoneNumber;

	private String email;

	private String pinyinShort;

	private String aliasName;

	private Long deptId;
	
	private Long positionId;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getPinyinShort() {
		return pinyinShort;
	}

	public void setPinyinShort(String pinyinShort) {
		this.pinyinShort = pinyinShort;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

}