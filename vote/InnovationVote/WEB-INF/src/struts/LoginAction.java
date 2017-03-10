package struts;

import db.fun.*;
import db.pojo.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;


public class LoginAction extends ActionSupport
{
	private String username;				//??¡꽍
	private String password;
 private String newpassword;     //???㞉private String newpassword;     //§⽿
	private String newpasswordcheck;//??§⽿
	public void setNewpassword(String newpassword)
	{
		this.newpassword=newpassword;
	}
	public String getNewpassword(String newpassword)
	{
		return this.newpassword;
	}
	public void setNewpasswordcheck(String newpasswordcheck)
	{
		this.newpasswordcheck=newpasswordcheck;
	}
	public String getNewpasswordcheck(String newpasswordcheck)
	{
		return this.newpasswordcheck;
	}

	public String getUsername()
	{
		return this.username;
  }
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getPassword(String password )
	{
		return this.password;
	}
	
	
	
	public String execute() throws Exception
	{
			//ActionContext.getContext().getSession().put("username","zhangsan");
		 	username =(String)ActionContext.getContext().getSession().get("project_id");
		String ret="INPUT";
		if(username.equals(""))
	 	{
			this.addFieldError(username,"未登录");
			return ret;
		 }
		
	 	if(password.equals(""))
	 	{
			this.addFieldError(password,"密码不能为空");
			return ret;
		}
		if(newpassword.equals(""))
	 	{
			this.addFieldError(newpassword,"新密码不能为空");
			return ret;
		}
		//checkpasswd(username, password)
		if(!new FunProjectData().checkProject(username,password))
		{
				this.addFieldError(username,"旧密码不正确");
				return ret;
		}
		if(!newpassword.equals(newpasswordcheck))
		{ 
			this.addFieldError(username,"两次密码不一致");
				return ret;
		}
		new FunProjectData().modifyProjectPassword(username,newpassword);
			ActionContext.getContext().getSession().put("project_id","");
			return ret="SUCCESS";
	}
		
}
