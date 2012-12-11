package com.mtea.yunwu.dao.sql;

import com.mtea.yunwu.model.core.BaseModel;

/**
 * 添加时间列可以过滤
 * @author macrotea@qq.com
 * @date 2012-12-4 上午12:32:38
 * @version 1.0
 * @note
 */
public class AddTimeColumnExcludable implements ColumnExcludable {

	/* (non-Javadoc)
	 * @see com.mtea.yunwu.utils.ColumnExcludable#isExclude(java.lang.String)
	 */
	@Override
	public boolean isExclude(String currColumn) {
		if (BaseModel.ADDTIME.equals(currColumn)) {
			return true;
		}
		return false;
	}

}
