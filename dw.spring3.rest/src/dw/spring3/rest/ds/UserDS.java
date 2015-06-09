package dw.spring3.rest.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import dw.spring3.rest.Resource.*;
import dw.spring3.rest.ResourceModel.*;
import dw.spring3.rest.bean.User;

public class UserDS
{
	private static Map<Long, User> allUsers;
	static
	{
		allUsers = new HashMap<Long, User>();
		User u1 = new User(1L, "Huang Yi Ming", 1);
		User u2 = new User(2L, "Wu Dong Fei", 2);
		allUsers.put(u1.getId(), u1);
		allUsers.put(u2.getId(), u2);
	}

	/**
	 * 调用resource 层，进行mysql操作
	 */
	private ResourceUser userResource;

	public void setUserResource(ResourceUser userResource)
	{
		this.userResource = userResource;
	}

	public void updateUserWithMysql(User u)
	{
		RUser user = new RUser();
		user.setId(u.getId());
		user.setName(u.getName());
		user.setSex(u.getYear());
		userResource.update(user);
	}

	public List<User> GetAllUsersWithMySql()
	{
		List users = userResource.getUser();
		List<User> lstUsers = new ArrayList<User>();
		for (Object u : users)
		{
			RUser rUser = (RUser) u;
			User user = new User();
			user.setId(rUser.getId());
			user.setName(rUser.getName());
			user.setYear(rUser.getSex());
			lstUsers.add(user);
		}
		return lstUsers;
	}
	
	public User GetUserById(long id){
		RUser rUser=userResource.GetUserById(id);
		if(rUser!=null){
			User user=new User();
			user.setId(rUser.getId());
			user.setName(rUser.getName());
			user.setYear(rUser.getSex());
			return user;
		}
		else {
			return null;
		}
	}

	public void add(User u)
	{
		allUsers.put(u.getId(), u);
	}

	public User get(long id)
	{
		return allUsers.get(id);
	}

	public List<User> getAll()
	{
		List<User> users = new ArrayList<User>();
		for (Iterator<User> it = allUsers.values().iterator(); it.hasNext();)
		{
			User u = it.next();
			users.add(u);
		}

		for (User user : allUsers.values())
		{
			System.out.print(user.getId());
		}
		return users;
	}

	public void remove(long id)
	{
		allUsers.remove(id);
	}

	public void update(User u)
	{
		allUsers.put(u.getId(), u);
	}
}
