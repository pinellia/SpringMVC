package dw.spring3.rest.controller;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dw.spring3.rest.bean.User;
import dw.spring3.rest.bean.UserList;
import dw.spring3.rest.ds.UserDS;

@Controller
public class UserController
{
	private UserDS userDS;

	public void setUserDS(UserDS ds)
	{
		this.userDS = ds;
	}

	private Jaxb2Marshaller jaxb2Mashaller;

	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller)
	{
		this.jaxb2Mashaller = jaxb2Mashaller;
	}

	private static final String XML_VIEW_NAME = "users";

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public ModelAndView getUser(@PathVariable String id)
	{
		// User u = userDS.get(Long.parseLong(id));
		User u = userDS.GetUserById(Long.parseLong(id));
		if (u == null)
		{
			u = new User();
		}
		return new ModelAndView(XML_VIEW_NAME, "object", u);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
	public ModelAndView updateUser(@RequestBody String body)
	{
		Source source = new StreamSource(new StringReader(body));
		User u = (User) jaxb2Mashaller.unmarshal(source);
		// userDS.update(u);
		userDS.updateUserWithMysql(u);
		return new ModelAndView(XML_VIEW_NAME, "object", u);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ModelAndView addUser(@RequestBody String body)
	{
		Source source = new StreamSource(new StringReader(body));
		User u = (User) jaxb2Mashaller.unmarshal(source);
		userDS.add(u);
		return new ModelAndView(XML_VIEW_NAME, "object", u);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public ModelAndView removeUser(@PathVariable String id)
	{
		userDS.remove(Long.parseLong(id));
		List<User> users = userDS.getAll();
		UserList list = new UserList(users);
		return new ModelAndView(XML_VIEW_NAME, "users", list);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public ModelAndView getUsers()
	{
		// List<User> users = userDS.getAll();
		List<User> users = userDS.GetAllUsersWithMySql();
		UserList list = new UserList(users);
		return new ModelAndView(XML_VIEW_NAME, "users", list);
	}

}
