package dw.spring3.rest.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class UserList
{

	private int count;

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	private List<User> users;

	@XmlElement(name = "user")
	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}

	public UserList()
	{
	}

	public UserList(List<User> users)
	{
		this.users = users;
		this.count = users.size();
	}
}
