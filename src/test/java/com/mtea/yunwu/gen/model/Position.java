/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu.gen.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mtea.yunwu.model.base.BaseModel;

/**
 * 职位
 * 
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-17 下午4:01:37
 */
@Entity
@Table(name = "tb_position")
public class Position extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String name;

	@ManyToMany(targetEntity = Dept.class, mappedBy = "positionSet")
	private Set<Dept> deptSet = new HashSet<Dept>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得 deptSet
	 * @return 
	 */
	public Set<Dept> getDeptSet() {
		return deptSet;
	}

	/**
	 * 设置 deptSet
	 * @param deptSet
	 */
	public void setDeptSet(Set<Dept> deptSet) {
		this.deptSet = deptSet;
	}
	

}
