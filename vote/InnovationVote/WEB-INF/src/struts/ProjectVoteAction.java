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

public class ProjectVoteAction extends ActionSupport
{
	private String UserNo;
	private Long ProjectID;
	public Date TimeNow;
	public Date dbDate;
	public Date TimeStandard;
	public Date TimeChecked;
	public String Err="";
	public int  VoteNum;
	private String UserIP="";//�õ��ͻ���ip��ַ
		public void setUserNo(String UserNo)
		{
			this.UserNo = UserNo;
		}
		
		public String getUserNo()
		{
			return this.UserNo;
		}
		
		public void setProjectID(Long ProjectID)
		{
			this.ProjectID = ProjectID;
		}
		 
		public Long getProjectID()
		{
		return this.ProjectID;
		}
			
		
		public Boolean TimeValid ()
		{
			this.TimeNow=new Date();
     	this.TimeChecked=new FunUserData().getUserDatetime(UserNo);
    	Calendar c1 = Calendar.getInstance(); 
			Date dbDate=this.TimeChecked;
			c1.setTime(dbDate);
			c1.add(Calendar.HOUR_OF_DAY, 6);
			this.TimeStandard = new Date();
			if( (this.TimeNow).after(this.TimeStandard) )
			return false;
			else  
			return true;
		}
			
		public Boolean ExistUserNo()
		{
			if(new FunUserData().ifUserID(userNo)&&new FunUserData().ifUserPassword(UserPassword) )                             
		 		return true;
		 	else 
		 		return false;
		}
		         
    public int VoteNum()                         
    {
    	return new FunUserData().getUserVoteNum(userNo);
    }                       
   
   	public Boolean Repeat()
    {
    	if(new FunVoteRecord().ifRepeat(projectID, userNo))         
   			return true;
   		else 
   			return false;
   	} 
 
 
	public String execute()
	{
		
     HttpServletRequest request = ServletActionContext.getRequest();
 
     UserIP=request.getRemoteAddr();
	
  	 if( !ExistUserNo())
  	 {
  		 Err="��������û��������벻��ȷ";
  		 return "success";
  	 }
  
  		
     if(!TimeValid())
     {
     	 Err="δ�м�¼���ѳ�ʱ������ȥǰ̨���¼�¼"; 
       return "success";}  
     
 	   if(new FunUserData().getUserVoteNum(userNo)>10)
 	   {
 	   	 Err="����10Ʊ��Ͷ��";
			 return "success"; }
 	  	
     if(Repeat()) 
     {
       Err="����Ͷ������Ŀ";
     	return "success"; 
     }
     
     request.setAttribute("tipMessage", Err);	   
     new FunUserData().vote(projectID, userNo, UserIP);
		 return  "success";
    }
}