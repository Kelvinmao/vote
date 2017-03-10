package struts;

import db.fun.*;
import db.pojo.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;


public class LoginAdmin extends ActionSupport
{
	private String username="";				//用户名
	private String password="";				//密码
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return this.username;
  }
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword(String password )
	{
		return this.password;
	}
	
	
	
	public String execute() throws Exception
	{
		
		String ret="INPUT";

		if(username.equals(""))
	 	{
			this.addFieldError(username,"用户名不能为空");
			ret="FAIL";
			return ret;
		}
		
	 	if(password.equals(""))
	 	{
			this.addFieldError(password,"密码不能为空");
			ret="FAIL";
			return ret;
		}
	
		if (username.equals("admin")&& password.equals("liangjing"))
		{
			ActionContext.getContext().getSession().put("username",this.username);
			ret="SUCCESS";
		}
		else
		{ 
			System.err.println("---");
			this.addFieldError(username,"用户名或密码不正确，登陆失败");
			ret="INPUT";
		}

		return ret;
	}	
}
