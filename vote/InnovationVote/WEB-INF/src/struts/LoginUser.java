package struts;

import db.fun.*;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;


public class LoginUser extends ActionSupport
{
	private String UserNo;
	
	public void setUserNo(String UserNo)
	{
			this.UserNo = UserNo;
	}
		
	public String getUserNo()
	{
			return this.UserNo;
	}
	
	
	public Boolean ExistUserNo()
	{  
		if(new FunUserData().ifUserID(UserNo))                      
		return true;
		else 
		return false;
	}
	
	
	
	public String execute() throws Exception
	{
		
		String ret="INPUT";

		if (!ExistUserNo())		
		{
			this.addFieldError(UserNo,"—ß∫≈ ‰»Î¥ÌŒÛ");
			return ret;
			
		}
		ActionContext.getContext().getSession().put("UserNo",this.UserNo);
		return "SUCCESS";	
	}
}