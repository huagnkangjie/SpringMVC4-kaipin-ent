package com.kaipin.sso.repository.mybatis.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.sso.common.page.GenericDefaultPage;
import com.kaipin.sso.common.page.IGenericPage;
import com.kaipin.sso.exception.BeanToMapException;
import com.kaipin.sso.util.bean.BeanMapUtil;

/**
 * 数据接入
 * 
 *
 * @param <T>
 *            数据对象
 * @param <ID>
 *            主键
 */
public abstract class MybatisBaseDAO<T, ID extends Serializable> extends SqlSessionDaoSupport {

	public static final String SQLNAME_SEPARATOR = ".";

	public static final String SQL_SAVE = "save";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GETBYID = "getById";
	public static final String SQL_DELETEBYID = "deleteById";
	public static final String SQL_DELETEBYIDS = "deleteByIds";
	public static final String SQL_FINDPAGEBY = "findPageBy";
	public static final String SQL_FINDLISTBY = "findListBy";
	public static final String SQL_GETCOUNTBY = "getCountBy";

	private static final String SORT_NAME = "SORT";

	private static final String DIR_NAME = "DIR";
	/** 不能用于SQL中的非法字符（主要用于排序字段名） */
	public static final String[] ILLEGAL_CHARS_FOR_SQL = { ",", ";", " ", "\"", "%" };
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);

		// this.sqlSessionTemplate = sqlSessionTemplate;

	}

	/**
	 * 获取默认SqlMapping命名空间。 使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
	 * 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
	 * 
	 * @return 返回命名空间字符串
	 */
	// @SuppressWarnings("unchecked")
	// protected String getDefaultSqlNamespace() {
	// Class<T> clazz = ReflectGeneric.getClassGenricType(this.getClass());
	// String nameSpace = clazz.getName();
	// return nameSpace;
	// }

	public abstract String getDefaultSqlNamespace();

	/**
	 * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * 
	 * @param sqlName
	 *            SqlMapping名
	 * @return 组合了SqlMapping命名空间后的完整SqlMapping名
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

	/**
	 * SqlMapping命名空间
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * 获取SqlMapping命名空间
	 * 
	 * @return SqlMapping命名空间
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * 设置SqlMapping命名空间。 此方法只用于注入SqlMapping命名空间，以改变默认的SqlMapping命名空间，
	 * 不能滥用此方法随意改变SqlMapping命名空间。
	 * 
	 * @param sqlNamespace
	 *            SqlMapping命名空间
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * 生成主键值。 默认情况下什么也不做； 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。
	 * 
	 * @param ob
	 *            要持久化的对象
	 */
	protected void generateId(T ob) {

	}

	/*
	 * (non-Javadoc)
	 *
	 */
	public Integer save(T ob) {
 
		return save(getSqlName(SQL_SAVE), ob);
	}
	
	public Integer save(String name,T ob) {
		generateId(ob);
		return this.getSqlSession().insert(getSqlName(SQL_SAVE), ob);
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 */
	public Integer update(T ob) {
		return this.getSqlSession().update(getSqlName(SQL_UPDATE), ob);
	}
 
	 
	
	/*
	 * (non-Javadoc)
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		return   this.getById(getSqlName(SQL_GETBYID), id);
	}

	
	public T getById(String name,ID id) {
		return (T) this.getSqlSession().selectOne(name, id);
	}

	
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 */
	public Integer deleteByIds(ID[] ids) {
		return deleteByIds(getSqlName(SQL_DELETEBYIDS), ids);
	}
	
	public Integer deleteByIds(String name,ID[] ids) {
		return this.getSqlSession().delete(name, ids);
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 */
	public Integer deleteById(ID id) {
		return this.deleteById(getSqlName(SQL_DELETEBYID), id);
	}
	public Integer deleteById(String name,ID id) {
		return this.getSqlSession().delete(name, id);
	}

	
	
	
	public IGenericPage<T> findPageBy(IBasePageSql sql, T param, int pageNo, int pageSize, String sort, String dir) {

		// 获取满足条件的记录总数，没有记录时返回空页数据

		String pageListSql;

		String pageCountSql;

		if (sql == null) {
			
			pageListSql = SQL_FINDPAGEBY;

			pageCountSql = SQL_GETCOUNTBY;

		} else {

			pageListSql = sql.getPageListSqlName();

			pageCountSql = sql.getPageCountSqlName();
		}

		int count = getCountBy(pageCountSql, param);

		if (count < 1) {
			return GenericDefaultPage.emptyPage();
		}

		Map<String, Object> paramMap = null;
		try {

			if (param == null) {
				paramMap = new HashMap<String, Object>();

			} else {

				paramMap = BeanMapUtil.bean2Map(param);

			}

		} catch (Exception e) {
			throw new BeanToMapException("获取参数失败", e);
		}
		// Where过滤条件
		// paramMap.put("param", param);
		// 排序条件

		if (sort != null) {
			// 排序字段不为空，过滤其中可能存在的非法字符
			sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
		}
		if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(dir)) {
			// paramMap.put("sort", null);
			// paramMap.put("dir", null);
		} else {
			paramMap.put(SORT_NAME, sort);
			
			paramMap.put(DIR_NAME, dir);
		}
		// 分页条件
		int start = GenericDefaultPage.getStartOfPage(pageNo, pageSize) - 1;
		RowBounds rowBound = new RowBounds(start, pageSize);

		List<T> lst = this.getSqlSession().selectList(getSqlName(pageListSql), paramMap, rowBound);

		return new GenericDefaultPage<T>(pageNo, pageSize, lst, count);

	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@SuppressWarnings("unchecked")
	public IGenericPage<T> findPageBy(T param, int pageNo, int pageSize, String sort, String dir) {
	 
		DefaultPageSql sql=new DefaultPageSql();
		
		return findPageBy(sql, param, pageNo, pageSize, sort, dir);
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public Integer getCountBy(T param) {

		String name = SQL_GETCOUNTBY;

		return getCountBy(name, param);
	}

	public Integer getCountBy(String name, T param) {
		Map<String, Object> paramMap = null;
		try {
			if (param == null) {
				paramMap = new HashMap<String, Object>();
			} else {
				paramMap = BeanMapUtil.bean2Map(param);
			}
		} catch (Exception e) {
			throw new BeanToMapException("获取参数失败", e);
		}
		// paramMap.put("param", param);
		Object o = this.getSqlSession().selectOne(getSqlName(name), paramMap);
		System.out.println(o);
		return (Integer) this.getSqlSession().selectOne(getSqlName(name), paramMap);
	}

	
	public List<T> findListBy(String name, T param, String sort, String dir) {

		Map<String, Object> paramMap = null;
		try {

			if (param == null) {
				paramMap = new HashMap<String, Object>();
			} else {
				paramMap = BeanMapUtil.bean2Map(param);
			}

		} catch (Exception e) {
			throw new BeanToMapException("获取参数失败", e);
		}
		// Where过滤条件
		// paramMap.put("param", param);
		// 排序条件

		if (sort != null) {
			// 排序字段不为空，过滤其中可能存在的非法字符
			sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
		}
		if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(dir)) {
			// paramMap.put("sort", null);
			// paramMap.put("dir", null);
		} else {
			paramMap.put(SORT_NAME, sort);
			paramMap.put(DIR_NAME, dir);
		}
		List<T> lst = this.getSqlSession().selectList(getSqlName(name), paramMap);
		return lst;

	}

	@SuppressWarnings("unchecked")
	public List<T> findListBy(T param, String sort, String dir) {
		String name = SQL_FINDLISTBY;
		return findListBy(name, param, sort, dir);
	}

	public List<T> findListBy(String name, T param) {
		return findListBy(name, param, null, null);
	}

	public List<T> findListBy(String name) {
		return findListBy(name, null, null, null);
	}

	/**
	 * 从给定字符串中将指定的非法字符串数组中各字符串过滤掉。
	 * 
	 * @param str
	 *            待过滤的字符串
	 * @param filterChars
	 *            指定的非法字符串数组
	 * @return 过滤后的字符串
	 */
	protected String filterIllegalChars(String str, String[] filterChars) {
		String rs = str;
		if (rs != null && filterChars != null) {
			for (String fc : filterChars) {
				if (fc != null && fc.length() > 0) {
					str = str.replaceAll(fc, "");
				}
			}
		}
		return rs;
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#insert(java.lang.String, java.lang.Object)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @return 执行结果——插入成功的记录数
	 * @see org.apache.ibatis.session.SqlSession#insert(java.lang.String,
	 *      java.lang.Object)
	 */
	protected int insert(String statement, Object parameter) {
		return this.getSqlSession().insert(getSqlName(statement), parameter);
	}

	/**
	 * 对{@link org.apache.ibatis.session.SqlSession#insert(java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @return 执行结果——插入成功的记录数
	 * @see org.apache.ibatis.session.SqlSession#insert(java.lang.String)
	 */
	protected int insert(String statement) {
		return this.getSqlSession().insert(getSqlName(statement));
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#update(java.lang.String, java.lang.Object)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @return 执行结果——更新成功的记录数
	 * @see org.apache.ibatis.session.SqlSession#update(java.lang.String,
	 *      java.lang.Object)
	 */
	protected int update(String statement, Object parameter) {
		return this.getSqlSession().update(getSqlName(statement), parameter);
	}

	/**
	 * 对{@link org.apache.ibatis.session.SqlSession#update(java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @return 执行结果——更新成功的记录数
	 * @see org.apache.ibatis.session.SqlSession#update(java.lang.String)
	 */
	protected int update(String statement) {
		return this.getSqlSession().update(getSqlName(statement));
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#delete(java.lang.String, java.lang.Object)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @return 执行结果——删除成功的记录数
	 * @see org.apache.ibatis.session.SqlSession#delete(java.lang.String,
	 *      java.lang.Object)
	 */
	protected int delete(String statement, Object parameter) {
		return this.getSqlSession().delete(getSqlName(statement), parameter);
	}

	/**
	 * 对{@link org.apache.ibatis.session.SqlSession#delete(java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @return 执行结果——删除成功的记录数
	 * @see org.apache.ibatis.session.SqlSession#delete(java.lang.String)
	 */
	protected int delete(String statement) {
		return this.getSqlSession().delete(getSqlName(statement));
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectList(java.lang.String, java.lang.Object, org.apache.ibatis.session.RowBounds)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @param rowBounds
	 *            用于分页查询的记录范围
	 * @return 查询结果列表
	 * @see org.apache.ibatis.session.SqlSession#selectList(java.lang.String,
	 *      java.lang.Object, org.apache.ibatis.session.RowBounds)
	 */
	protected List selectList(String statement, Object parameter, RowBounds rowBounds) {
		return this.getSqlSession().selectList(getSqlName(statement), parameter, rowBounds);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectList(java.lang.String, java.lang.Object)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @return 查询结果列表
	 * @see org.apache.ibatis.session.SqlSession#selectList(java.lang.String,
	 *      java.lang.Object)
	 */
	protected List selectList(String statement, Object parameter) {
		return this.getSqlSession().selectList(getSqlName(statement), parameter);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectList(java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @return 查询结果列表
	 * @see org.apache.ibatis.session.SqlSession#selectList(java.lang.String)
	 */
	protected List selectList(String statement) {
		return this.getSqlSession().selectList(getSqlName(statement));
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectOne(java.lang.String, java.lang.Object)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @return 查询结果对象
	 * @see org.apache.ibatis.session.SqlSession#selectOne(java.lang.String,
	 *      java.lang.Object)
	 */
	protected Object selectOne(String statement, Object parameter) {
		return this.getSqlSession().selectOne(getSqlName(statement), parameter);
	}

	/**
	 * 对{@link org.apache.ibatis.session.SqlSession#selectOne(java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @return 查询结果对象
	 * @see org.apache.ibatis.session.SqlSession#selectOne(java.lang.String)
	 */
	protected Object selectOne(String statement) {
		return this.getSqlSession().selectOne(getSqlName(statement));
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectMap(java.lang.String, java.lang.Object, java.lang.String, org.apache.ibatis.session.RowBounds)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @param mapKey
	 *            数据mapKey
	 * @param rowBounds
	 *            用于分页查询的记录范围
	 * @return 查询结果Map
	 * @see org.apache.ibatis.session.SqlSession#selectMap(java.lang.String,
	 *      java.lang.Object, java.lang.String,
	 *      org.apache.ibatis.session.RowBounds)
	 */
	protected Map selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return this.getSqlSession().selectMap(getSqlName(statement), parameter, mapKey, rowBounds);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectMap(java.lang.String, java.lang.Object, java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @param mapKey
	 *            数据mapKey
	 * @return 查询结果Map
	 * @see org.apache.ibatis.session.SqlSession#selectMap(java.lang.String,
	 *      java.lang.Object, java.lang.String)
	 */
	protected Map selectMap(String statement, Object parameter, String mapKey) {
		return this.getSqlSession().selectMap(getSqlName(statement), parameter, mapKey);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#selectMap(java.lang.String, java.lang.String)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param mapKey
	 *            数据mapKey
	 * @return 查询结果Map
	 * @see org.apache.ibatis.session.SqlSession#selectMap(java.lang.String,
	 *      java.lang.String)
	 */
	protected Map selectMap(String statement, String mapKey) {
		return this.getSqlSession().selectMap(getSqlName(statement), mapKey);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#select(java.lang.String, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @param rowBounds
	 *            用于分页查询的记录范围
	 * @param handler
	 *            结果集处理器
	 * @see org.apache.ibatis.session.SqlSession#select(java.lang.String,
	 *      java.lang.Object, org.apache.ibatis.session.RowBounds,
	 *      org.apache.ibatis.session.ResultHandler)
	 */
	protected void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		this.getSqlSession().select(getSqlName(statement), parameter, rowBounds, handler);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#select(java.lang.String, java.lang.Object, org.apache.ibatis.session.ResultHandler)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param parameter
	 *            参数
	 * @param handler
	 *            结果集处理器
	 * @see org.apache.ibatis.session.SqlSession#select(java.lang.String,
	 *      java.lang.Object, org.apache.ibatis.session.ResultHandler)
	 */
	protected void select(String statement, Object parameter, ResultHandler handler) {
		this.getSqlSession().select(getSqlName(statement), parameter, handler);
	}

	/**
	 * 对
	 * {@link org.apache.ibatis.session.SqlSession#select(java.lang.String, org.apache.ibatis.session.ResultHandler)}
	 * 的代理。 将statement包装了命名空间，方便DAO子类调用。
	 * 
	 * @param statement
	 *            映射的语句ID
	 * @param handler
	 *            结果集处理器
	 * @see org.apache.ibatis.session.SqlSession#select(java.lang.String,
	 *      org.apache.ibatis.session.ResultHandler)
	 */
	protected void select(String statement, ResultHandler handler) {
		this.getSqlSession().select(getSqlName(statement), handler);
	}

}
