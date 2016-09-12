package com.moment.dao.userInfo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moment.beans.BaseBean;
import com.moment.beans.userInfo.ThirdPartyLogging;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.BaseDao;

/**
 * @author YeFeng
 * 2015 4.2
 * 用户登录Dao
 */
@Repository
public class SignInDao extends BaseDao<UserInfo>{

	private static final Log log = LogFactory.getLog(SignInDao.class);
	/**
	 * @author YeFeng
	 * 获取指定账户的用户信息
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> getInfoByPointAccount(String phone_num)
	{
		try 
		{
			//return 	queryForList("from UserInfo");
			String hql = "from UserInfo k where k.phone_num = '" + phone_num + "'";
			Query query = this.getSession().createQuery(hql);
			return query.list();
			
		} catch (Exception e) {
			log.error("用户登录异常-- 用户名(手机号)：" + phone_num + " \\ " + e.getMessage());
			return null;
		}
	}
	
	public ThirdPartyLogging queryThirdPartLogInfo(String third_party_identify)
	{
		String hql = "from ThirdPartyLogging k where k.third_party_identify = '" + third_party_identify + "'";
		Query query = this.getSession().createQuery(hql);
		return (ThirdPartyLogging) query.uniqueResult();
	}
	
	/**
	 * 数据插入数据库
	 * @param data
	 * @return
	 */
	public Integer insertData(BaseBean data)
    {
    	try
    	{
    	    this.getSession().save(data);
    	    Integer id = data.getId();
    		return id;
    	}
    	catch(Exception e)
    	{
    		log.error("第三方登录信息插入数据库失败--" + data.toString() + " \\ " + e.getMessage());
    		return -1;
    	}
    }
	
	@Transactional 
	public Integer updateThirdPartData(Integer userId,String user_name,String head_img,int sex,String time)
	{
		try
		{	
			Session session = this.getSession();
			UserInfo userInfo = (UserInfo)session.get(UserInfo.class, userId);
			userInfo.setHead_image(head_img);
			userInfo.setSex(sex);
			userInfo.setUser_name(user_name);
			userInfo.setLast_login_time(time);
			update(userInfo);
			return 1;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
}
