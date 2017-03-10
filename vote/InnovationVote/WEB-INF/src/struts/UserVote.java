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

public class UserVote extends ActionSupport
{
    List list = null;
    String UserNo;
    
    public void setList(List list)
	  {
			this.list = list;
	  }
		
		public List getList()
	  {
			return this.list;
	  }
	  
	  public void setUserNo(String UserNo)
	  {
			this.UserNo = UserNo;
	  }
		
		public String getUserNo()
	  {
			return this.UserNo;
	  }
	  
    public String execute() throws Exception
    {
        String outID = null;
        
        if(UserNo.length() == 8)
        {
        	outID = new FunUserData().getOutID(UserNo);     //??
        	list = new FunUserData().userPoll(outID);
        }
        else
        {
        	list = new FunUserData().userPoll(UserNo);
        }
        
        HttpServletRequest request = ServletActionContext.getRequest();
     
        request.setAttribute("list", list);
        
        return SUCCESS;
    }
}