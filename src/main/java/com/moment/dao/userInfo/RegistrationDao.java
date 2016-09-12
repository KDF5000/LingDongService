package com.moment.dao.userInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.moment.beans.BaseBean;
import com.moment.beans.userInfo.UserInfo;
import com.moment.beans.userInfo.UserOtherInfo;
import com.moment.dao.BaseDao;

@Repository
public class RegistrationDao extends BaseDao<UserInfo>{
	
	private static final Log log = LogFactory.getLog(RegistrationDao.class);

	/**
	 * 数据插入数据库
	 * @param data
	 * @return
	 */
    public Integer insertData(BaseBean data)
    {
    	try
    	{
    		log.info("注册信息，数据插入数据库--" + data.toString());
    	    this.getSession().save(data);
    	    Integer id = data.getId();
    		return id;
    	}
    	catch(Exception e)
    	{
    		log.error("注册信息，数据插入数据库失败--" + data.toString() + " \\ " + e.getMessage());
    		return -1;
    	}
    }
    
	/**
	 * 根据用户id获取用户其他信息
	 * @param userId
	 * @return
	 */
	public UserOtherInfo getUserOtherInfoById(Integer userId)
	{
		try
		{
			String hql = "from UserOtherInfo where user_id = ?";
			Object[] params = new Object[1];
			params[0] = userId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			return (UserOtherInfo) query.uniqueResult();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 更新数据
	 * @param data
	 * @return
	 */
	public boolean updateData(BaseBean data)
	{
		try
		{
			update(data);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * 更新数据
	 * @param data
	 * @return
	 */
	public boolean isExitPhoneNum(String mobiePhone)
	{
		try
		{
			String hql = "from UserInfo where phone_num = ?";
			Object[] params = new Object[1];
			params[0] = mobiePhone;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			if(1 == query.list().size())
				return true;
			else return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
