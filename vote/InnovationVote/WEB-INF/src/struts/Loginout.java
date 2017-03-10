package struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
public class Loginout extends ActionSupport
{
	private String username;
	private String UserNo;
	
	public void setUsername(String username)
	{
			this.username = username;
	}
	
	public String getUsername()
	{
			return this.username;
	}
	
	public void setUserNo(String UserNo)
	{
			this.UserNo = UserNo;
	}
	
	public String getUserNo()
	{
			return this.UserNo;
	}
	
	public String execute()
	{
		System.out.println(UserNo);
		System.out.println(username);
		ActionContext.getContext().getSession().put("UserNo","");
		return "SUCCESS";
	}
}