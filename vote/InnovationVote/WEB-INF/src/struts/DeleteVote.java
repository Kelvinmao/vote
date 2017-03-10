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

public class DeleteVote extends ActionSupport
{
    Long ProjectID;
    String UserNo;
    String Err;
    
    public void setErr(String Err)
	  {
			this.Err = Err;
	  }
		
		public String getErr()
	  {
			return this.Err;
	  }
	  
    public void setProjectID(Long ProjectID)
	  {
			this.ProjectID = ProjectID;
	  }
		
		public Long getProjectID()
	  {
			return this.ProjectID;
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
        String ret="INPUT";
        
        if( !new FunVoteRecord().ifRepeat(ProjectID,UserNo) )
        {
        	this.addFieldError(UserNo,"没有此投票记录");
        	return ret;
        }
        
        if(!new FunUserData().ifUserID(UserNo))
        {
        		this.addFieldError(UserNo,"学号输入错误");
						return ret;
        }
        
        if( !new FunUserData().ifProjectID(ProjectID))
  			{
  					this.addFieldError(UserNo,"没有此项目");
			    	return ret;
  			}
       
        new FunVoteRecord().voteDelete(ProjectID,UserNo);
        System.out.println(ProjectID);
        System.out.println(UserNo);
        
        
        return SUCCESS;
    }
}