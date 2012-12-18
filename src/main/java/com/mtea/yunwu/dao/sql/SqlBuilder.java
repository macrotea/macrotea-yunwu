package com.mtea.yunwu.dao.sql;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.mtea.yunwu.global.Constants;
import com.mtea.yunwu.model.base.BaseModel;
import com.mtea.yunwu.model.ext.SqlArgsBean;
import com.mtea.yunwu.utils.ModelInspector;
import com.mtea.yunwu.utils.ReflectUtils;

/**
 * SQL构建器
 * @author macrotea@qq.com
 * @date 2012-11-29 下午11:46:37
 * @version 1.0
 * @param <T>
 * @note
 */
public class SqlBuilder {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ModelInspector modelInspector;

	private SqlBuilder(ModelInspector modelInspector) {
		this.modelInspector = modelInspector;
	}

	/**
	 * 语义化构造实例
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午8:08:54
	 * @param modelInspector
	 * @return
	 */
	public static SqlBuilder use(ModelInspector modelInspector) {
		return new SqlBuilder(modelInspector);
	}

	/**
	 * 根据Id删除
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午8:08:40
	 * @return
	 */
	public String toDeleteByIdSql() {
		return String.format("DELETE FROM %s WHERE %s = ?", modelInspector.getTableName(), modelInspector.getPKColumn());
	}

	/**
	 * 删除所有
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午8:08:36
	 * @return
	 */
	public String toDeleteAllSql() {
		return String.format("DELETE FROM %s", modelInspector.getTableName());
	}

	/**
	 * 查找所有
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午8:08:29
	 * @return
	 */
	public String toFindAllSql() {
		return String.format("SELECT * FROM %s WHERE 1=1", modelInspector.getTableName());
	}

	/**
	 * 根据Id查找
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午8:08:21
	 * @return
	 */
	public String toFindByIdSql() {
		return String.format("SELECT * FROM %s WHERE %s = ?", modelInspector.getTableName(), modelInspector.getPKColumn());
	}

	/**
	 * 更新
	 * @author macrotea@qq.com
	 * @param <T>
	 * @date 2012-11-30 下午8:08:13
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IntrospectionException 
	 */
	public <T extends BaseModel> SqlArgsBean createUpdateSqlArgsBean(T model) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		List<Object> argsList = new ArrayList<Object>();
		String[] columnNames = modelInspector.getCommonColumns();
		StringBuilder builder =new StringBuilder();
		builder.append("UPDATE").append(Constants.BLANK).append(modelInspector.getTableName()).append(Constants.BLANK).append("SET");
		
		//拼接SET XXXX=? , XXXX=?
		int len =columnNames.length ;
		for (int i = 0; i < columnNames.length; i++) {
			String columnName = columnNames[i];
			Object value = ReflectUtils.getProperty(model, columnName);
			argsList.add(value);
			builder.append(Constants.BLANK).append(columnNames[i]);
			if (i != len - 1) {
				builder.append(" = ? ,");
			} else {
				builder.append(" = ?");
			}
		}
		
		//拼接WHERE id = ?
		builder.append(Constants.BLANK).append("WHERE").append(Constants.BLANK).append(modelInspector.getPKColumn()).append("= ?");
		argsList.add(ReflectUtils.getProperty(model, modelInspector.getPKColumn()));
		
		String sql = builder.toString();
		logger.trace("createUpdateSqlArgsBean() - update sql : "+ sql);
		logger.trace("createUpdateSqlArgsBean() - update sql argsList: "+ (argsList));
		return new SqlArgsBean(sql,argsList);
	}

	/**
	 * 计算所有
	 * @author macrotea@qq.com
	 * @date 2012-11-30 下午9:34:55
	 * @return
	 */
	public String toCountAllSql() {
		return String.format("SELECT COUNT(*) FROM %s WHERE 1=1", modelInspector.getTableName());
	}

	/**
	 * 获得带有命名参数的根据Id更新SQL
	 * @author macrotea@qq.com
	 * @date 2012-12-1 下午8:01:52
	 * @return
	 */
	public String toUpdateByIdSql() {
		String[] columnNames = modelInspector.getCommonColumns();
		StringBuilder builder =new StringBuilder();
		builder.append("UPDATE").append(Constants.BLANK).append(modelInspector.getTableName()).append(Constants.BLANK).append("SET");
		
		//拼接SET XXXX=:XXXX , XXXX=:XXXX
		int len =columnNames.length ;
		for (int i = 0; i < columnNames.length; i++) {
			String columnName = columnNames[i];
			builder.append(Constants.BLANK).append(columnNames[i]);
			if (i != len - 1) {
				builder.append(" =:").append(columnName).append(" ,");
			} else {
				builder.append(" =:").append(columnName);
			}
		}
		
		//拼接WHERE id = :id
		builder.append(Constants.BLANK).append("WHERE").append(Constants.BLANK).append(modelInspector.getPKColumn()).append("=:").append(modelInspector.getPKColumn());
		
		return builder.toString();
	}
	
	
	/**
	 * <pre>
	 * 构建SQL参数及参数值的映射
	 * 用法:
	 * SqlParameterSource paramSource = buildSqlParamsMapping("code", code,"version", version);
	 * </pre>
	 * @param params
	 * @return
	 * @author liangqiye / 2012-12-18 上午9:59:33
	 */
	public SqlParameterSource buildSqlParamsMapping(Object... params) {
		if (params == null || params.length == 0) {
			return null;
		}
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		//注意跳跃 : i += 2
		for (int i = 0; i < params.length; i += 2) {
			paramSource.addValue((String) params[i], params[i + 1]);
		}
		
		return paramSource;
	}

}
