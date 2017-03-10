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

public class LoginWithCard extends ActionSupport
{  
	private String cardSource;
	private Date datetime;
	
	public void setCardSource(String cardSource)
	{
			this.cardSource = cardSource;
	}
		
	public String getCardSource()
	{
			return this.cardSource;
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
			String ret="INPUT";
			String outID = new FunUserData().getOutID(cardSource);
			if(outID == "FFFFFFFF")
			{
			
			this.addFieldError(cardSource,"���Ŵ���");
			
			return ret;
			}
			datetime = new Date();
			new FunUserData().setUserDatetime(outID,datetime);
			this.addFieldError(cardSource,"��½�ɹ�");
			return ret;
	}
}