package struts;

//import java.util.Date;
//import java.util.Calendar; 
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  
import java.io.PrintWriter;
import  db.fun.*;
import  db.pojo.*;
public class Vote extends ActionSupport
{  
	private String UserNo;
	private String ProjectID;
	private String password;
	
  
	public Date TimeNow;
	public Date dbDate;
	public Date TimeStandard;
	public Date TimeChecked;
	public String Err="";
	public int  VoteNum;
	private String UserIP="";//�õ��ͻ���ip��ַ
	
		public void setProjectID(String ProjectID)
		 {
			this.ProjectID = ProjectID;
		 }
		 
		public String  getProjectID()
		{
		return this.ProjectID;
		}
		public void setUserNo(String UserNo)
		 {
			this.UserNo = UserNo;
		 }
		 
		public String  getUserNo()
		{
		return this.UserNo;
		}
		public void setPassword(String password)
		 {
			this.password = password;
		 }
		 
		public String  getPassword()
		{
		return this.password;
		}
		
	    //book user=new book();  user.field(UserNo);//�������ֱ����UserNo���������������ݿ���ȷ��������������������
	   // user.getTimeChecked();  //���ݿ��ж�ȡUserNo��Ӧ��TimeChecked
		public Boolean TimeValid ()
		{
		this.TimeNow=new Date();
     	this.TimeChecked=new FunUserData().getUserDatetime(UserNo);
     	Calendar c1 = Calendar.getInstance(); 
		Date dbDate=this.TimeChecked;
		c1.setTime(dbDate);
		c1.add(Calendar.HOUR_OF_DAY, 6);
		this.TimeStandard = c1.getTime();
		if( (this.TimeNow).after(this.TimeStandard) )
		return false;
		else  
		return true;
		
		}
  // public void VoteNum() //�����ݿ�õ�ͶƱ��    
   //{ this.VoteNum=new FunUserData().getUserVoteNum(UserNo);}           
   
   public Long VoteNum()                          //�õ�ͶƱ��    
   {return new FunUserData().getPollNumber(UserNo);}                       // user.getVotenum();//�õ�Ʊ��
   
   
   public Boolean Repeat()
   {if(new FunPollData().ifRepeat(ProjectID, UserNo))         //user.Repeat()//��ͶƱ�Ƿ��ظ�
   	return true;
   	else 
   	return false;
   	} 
 
 
	public String execute()
	{
		 ActionContext.getContext().getSession().put("userID",UserNo);
		 ActionContext.getContext().getSession().put("password",password);
     HttpServletRequest request = ServletActionContext.getRequest();
 
      UserIP=request.getRemoteAddr();
     
	
	//try {
  	if(!new FunUserData().checkUser(UserNo,password))
  	{
  		 Err="��������û��������벻��ȷ";
  		 try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write("fail");}
       catch(Exception e)  {
	    System.out.println("�����쳣����½ʧ�ܣ�");}
  		return null;
  	}
  	if( !new FunUserData().checkDoubleID(ProjectID,UserNo))
  	{
  			Err="û�д���Ŀ";
  			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write("fail");}
       catch(Exception e)  {
	    System.out.println("�����쳣����½ʧ�ܣ�");}
  			return null;
  	}
  
  	 if(new FunUserData().getUserIsBUPT(UserNo)==1L)
  	 {
     if(!TimeValid()){
     	 Err="δ�м�¼���ѳ�ʱ������ȥǰ̨���¼�¼";
     	 try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write("timeout");}
       catch(Exception e)  {
	    System.out.println("�����쳣����½ʧ�ܣ�");}
     return null;}
     }
     
 	  if(new FunUserData().getPollNumber(UserNo)>=10)//VoteNumLimitͶƱ����=10Ʊ
 	  {Err="����10Ʊ��Ͷ��";
 	  	try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write("voteout");}
       catch(Exception e)  {
	    System.out.println("�����쳣����½ʧ�ܣ�");}
 	  	return null; }
 	  	
    	    if(Repeat()) {
       Err="����Ͷ������Ŀ";
       try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write("repeat");}
       catch(Exception e)  {
	    System.out.println("�����쳣����½ʧ�ܣ�");}	
     	return null; }
    		
   //------------------------------------------------------------------------------ 		
   //�жϳɹ�������ͶƱ	   
  
       new FunUserData().vote(ProjectID, UserNo, UserIP);
        Long ret=10L-new FunUserData().getPollNumber(UserNo);
        try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(Long.toString(ret));}
       catch(Exception e)  {
	    System.out.println("�����쳣����½ʧ�ܣ�");}	   	   
 

       	
         return  null;
      
//catch(Exception e)  {
	//System.out.println("�����쳣����½ʧ�ܣ�");}					
    //	 return "login";
    //}
}
}