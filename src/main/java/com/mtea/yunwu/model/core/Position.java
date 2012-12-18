/**
 * Copyright (C) 2012 macrotea@qq.com Inc., All Rights Reserved.
 */
package com.mtea.yunwu.model.core;

import com.mtea.yunwu.model.base.BaseModel;


/**
 * 职位
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-17 下午4:01:37
 */
public class Position extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Long deptId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
