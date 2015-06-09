/**
 * @author Pinellia
 *
 */
package dw.spring3.rest.Resource;

import java.sql.SQLException;
import dw.spring3.rest.ResourceModel.RUser;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.util.*;

public class ResourceUser
{

	static com.ibatis.sqlmap.client.SqlMapClient sqlMap = null;

	static
	{
		try
		{
			String resource = "SqlMapConfig.xml";
			java.io.Reader reader = com.ibatis.common.resources.Resources
					.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e)
		{
			// TODO: handle exception
			System.out.println(e);
		}
	}

	/**
	 * 用户信息更新方法 add by pinellia 20150608
	 */
	public void update(RUser user)
	{
		try
		{
			sqlMap.startTransaction();
			sqlMap.update("updateUser", user);
			sqlMap.commitTransaction();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		} finally
		{
			try
			{
				sqlMap.endTransaction();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * add by pinellia 20150608 获取用户列表 暂时注释
	 */
	public List getUser()
	{
		List user = null;
		try
		{
			sqlMap.startTransaction();
			user = sqlMap.queryForList("getAllUser", null);
			sqlMap.commitTransaction();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		} finally
		{
			try
			{
				sqlMap.endTransaction();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return user;
	}
	
	/**
	 * add by pinellia 20150608
	 * @param id
	 * @return
	 */
	public RUser GetUserById(long id){
		RUser rUser=null;
		try
		{
			sqlMap.startTransaction();
			rUser = (RUser)sqlMap.queryForObject("getUserById", id);
			sqlMap.commitTransaction();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		} finally
		{
			try
			{
				sqlMap.endTransaction();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

        return rUser;
	}

}