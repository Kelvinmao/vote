package struts;

import db.fun.*;
import db.pojo.*;

import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext; 

public class LoginWithOutCard extends ActionSupport
{  
	private String userNo;
	private String name;
	private Date datetime;
	public String Err="";
	
	public void setUserNo(String userNo)
	{
			this.userNo = userNo;
	}
		
	public String getUserNo()
	{
			return this.userNo;
	}
	
	public void setName(String name)
	{
			this.name = name;
	}
		
	public String getName()
	{
			return this.name;
	}
	
	public void setDatetime(Date datetime)
	{
			this.datetime = datetime;
	}
		
	public Date getDatetime()
	{
			return this.datetime;
	}

	
	
	public String execute()
	{
		System.out.println(name);
			if( !new FunUserData().isTrueUser(userNo,name))
  	{
  		 Err="学号和姓名不匹配";
  		return "fail";
  	}
			datetime = new Date();
			new FunUserData().setUserDatetime(userNo,datetime);
			Err="恭喜您登陆成功";
			return "success";
	}
}