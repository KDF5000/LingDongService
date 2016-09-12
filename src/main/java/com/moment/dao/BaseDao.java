package com.moment.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.userInfo.UserInfo;

/**
 * 
 * @author YeFeng
 * @param <T>
 */
public class BaseDao<T> {

	private static final Log logs = LogFactory.getLog(BaseDao.class);
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T queryById(Class<T> entityClass, Integer id) {
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("hiding")
	public <T> void save(T t) {
		Session session = this.getSession();
		session.beginTransaction();
		session.save(t);
		session.flush();
		session.getTransaction().commit();
	}

	@SuppressWarnings("hiding")
	public <T> void update(T t) {
		Session session = this.getSession();
		session.beginTransaction();
		session.update(t);
		session.flush();
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("hiding")
	public <T> void delete(T t) {
		getSession().delete(t);

	}

	@SuppressWarnings("hiding")
	public <T> void delete(Class<T> entityClass, Integer id) {
		Session session = this.getSession();
		session.beginTransaction();
		session.delete(queryById(entityClass, id));
		session.flush();
		session.getTransaction().commit();

	}
	/**
	 * 
	* @Title: deleteByProperty   
	* @Description: 删除数据  
	* @param @param entityClass 实体类
	* @param @param property   要删除的表字段
	* @param @param 表字段的值
	* @return void      
	* @throws
	 */
	public void deleteByProperty(Class<T> entityClass,String property, String value) {
		String hql = "delete from " + entityClass.getSimpleName()
				+ " where " + property + "='" + value + "'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	/**
	 * 
	 * @param <T>
	 * @param hql
	 * @param entityClass
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> queryForList(String hql) {
		return queryForList(hql, new Object[] {});
	}

	/**
	 * 
	 * @param <T>
	 * @param hql
	 * @param entityClass
	 * @param param
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> queryForList(String hql, Object param) {
		return queryForList(hql, new Object[] { param });

	}

	/**
	 * 
	 * @param <T>
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> queryForList(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (List<T>) query.list();

	}
	
	/**
	 * 
	 * @param <T>
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryForObject(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T)query.uniqueResult();

	}
	
	@SuppressWarnings("hiding")
	public <T> List<T> findByPage(String hql,
			int firstResult, int maxResult) {
		return findByPage(hql, new Object[] {}, firstResult,
				maxResult);

	}

	@SuppressWarnings("hiding")
	public <T> List<T> findByPage(String hql,
			Object param, int firstResult, int maxResult) {
		return findByPage(hql, new Object[] { param },
				firstResult, maxResult);

	}

	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> List<T> findByPage(String hql,
			Object[] params, int firstResult, int maxResult) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		query.setCacheable(true); // 设置缓存 
		return (List<T>) query.list();

	}
	
	public void setQueryParams(Query query, Object[] params) {
		if (null == params) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}  
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> getByPage(String hql,
			Object[] params, int page, int pagesize) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.setFirstResult((page-1)*pagesize);
		query.setMaxResults(pagesize);
		query.setCacheable(true); // 设置缓存 
		return (List<T>) query.list();

	}
	
	/**
	 * 判断一个用户是否对某条灵感有点赞、收藏等操作
	 * @param user_id
	 * @return
	 */
	public boolean isOperated(String classType,int user_id,int moment_id)
	{
		try
		{
			String sql = "FROM " + classType + " where user_id = ? and moment_id = ?";
			Object[] params = new Object[2];
			params[0] = user_id;
			params[1] = moment_id;
			Query query = this.getSession().createQuery(sql);
			this.setQueryParams(query, params);
			if(0 == query.list().size())
				return false;
			return true;
		}
		catch(Exception e)
		{
			logs.error("获取用户的某操作（点赞，收藏等）     ----------------- " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 根据两个用户id判断两人是否有关注与被关注关系
	 * @param user_id
	 * @param attention_userId
	 * @return
	 */
	public boolean getUserRelationship(int user_id,int attention_userId)
	{
		try
		{
			String hql = "from UserRelationship where user_id = ? and attention_userId = ?";
			Object[] params = new Object[2];
			params[0] = user_id;
			params[1] = attention_userId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			if(0 == query.list().size())
				return false;
			return true;
		}
		catch(Exception e)
		{
			logs.error("关注好友的灵感动态中好友关系获取异常     ----------------- " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoById(Integer userId)
	{
		try
		{
			return queryById(UserInfo.class,userId);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 根据灵感id获取灵感内容
	 * @param momentId
	 * @return
	 */
	public Moments getMomentById(Integer momentId)
	{
		try
		{
			return queryById(Moments.class,momentId);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 根据灵感id获取评论内容
	 * @param momentId
	 * @return
	 */
	public Comments getCommentsById(Integer commentId)
	{
		try
		{
			return queryById(Comments.class,commentId);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 根据灵感id获取灵感的所有围观者
	 * @param momentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllWatchPerson(Integer momentId)
	{
		try
		{
			String sql = "select user_id from Collects where moment_id = ?";
			Object[] params = new Object[1];
			params[0] = momentId;
			Query query = this.getSession().createQuery(sql);
			this.setQueryParams(query, params);
			return query.list();
		}
		catch(Exception e)
		{
			logs.equals("获取灵感所有的围观者失败  momentId : " + momentId + " ----// " + e.getMessage());
			return null;
		}
	}
}

