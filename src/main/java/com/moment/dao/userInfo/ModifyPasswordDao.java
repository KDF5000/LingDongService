package com.moment.dao.userInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.BaseDao;

public class ModifyPasswordDao extends BaseDao<UserInfo>{

	private static final Log log = LogFactory.getLog(ModifyPasswordDao.class);
	
	/**
	 * 查询一个账户是否存在
	 * @param phone_num 用户唯一标识，手机号
	 * @return
	 */
	public boolean isAccountExist(String phone_num)
	{
		try 
		{
			String hql = "from UserInfo k where k.phone_num = '" + phone_num + "'";
			Query query = this.getSession().createQuery(hql);
			if(0 == query.list().size())
				return false;
			return true;
			
		} catch (Exception e) {
			log.error("账户查询异常-- 用户名(手机号)：" + phone_num + " \\ " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 修改密码
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public boolean modifyPassword(String phoneNum,String password)
	{
		try 
		{
			Session session = this.getSession();
			session.beginTransaction();
			String sql = "update UserInfo set passwords = '" + password + "' where phone_num = '" + phoneNum + "'";
			Query query = session.createQuery(sql);
			query.executeUpdate();  
	        session.getTransaction().commit(); 
	        return true;
			
		} catch (Exception e) {
			log.error("账户密码修改错误-- 用户名(手机号)：" + phoneNum + " \\ " + e.getMessage());
			return false;
		}
	}
}
