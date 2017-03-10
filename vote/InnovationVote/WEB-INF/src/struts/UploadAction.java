package struts;

//import java.util.Date;
//import java.util.Calendar; 
import java.util.*;
import java.io.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.apache.struts2.ServletActionContext;  
import  db.fun.*;
public class UploadAction extends ActionSupport
{  
	private File project_img;
	private String project_imgContentType;
	private String project_imgFileName;
	private String savePath;
	private String project_title;
	private String project_type;
	private String main_partner;
	private String other_partner;
	private String project_introduction;
	//private String CAPTHA;
	private String project_id;					
	
	/*public void setProject_id(String project_id)
	{
		this.project_id = project_id;
	}
	public String getProject_id()
	{
		return this.project_id;
  }*/
	
	public void setProject_title(String project_title)
	{
		this.project_title = project_title;
	}
	public String getProject_title()
	{
		return this.project_title;
  }
  
  	public void setProject_type(String project_type)
	{
		this.project_type = project_type;
	}
	public String getProject_type()
	{
		return this.project_type;
  }
  
  	public void setMain_partner(String main_partner)
	{
		this.main_partner = main_partner;
	}
	public String getMain_partner()
	{
		return this.main_partner;
  }
  
  	public void setOther_partner(String other_partner)
	{
		this.other_partner = other_partner;
	}
	public String getOther_partner()
	{
		return this.other_partner;
  }
  
  	public void setProject_introduction(String project_introduction)
	{
		this.project_introduction = project_introduction;
	}
	public String getProject_introduction()
	{
		return this.project_introduction;
  }
  
  /*public void setCAPTHA(String CAPTHA)
	{
		this.CAPTHA = CAPTHA;
	}
	public String getCAPTHA()
	{
		return this.CAPTHA;
  }*/
  
	private String getSavePath() throws Exception
	{
			HttpServletRequest request=ServletActionContext.getRequest();
			return request.getRealPath(savePath);
	}
	public void setSavePath(String value)
	{
		this.savePath=value;
	}
	
	public File getProject_img()
	{
		  return project_img;
	}
	public void setProject_img(File project_img)
	{
		this.project_img=project_img;
	}
	
	public String getProject_imgContentType()
	{
		return project_imgContentType;
	}
	public void setProject_imgContentType(String showimgContextType)
	{
		this.project_imgContentType=project_imgContentType;
		
	}
	
	public String getProject_imgFileName()
	{
		return project_imgFileName;
	}
	public void setProject_imgFileName(String showimgFileName)
	{
		this.project_imgFileName=showimgFileName;	
	}

	
	public String execute() throws Exception
	{
		  // ActionContext.getContext().getSession().put("project_id",this.project_id);
		  project_id=ActionContext.getContext().getSession().get("project_id").toString();
			String now_showingFileName=project_id+getProject_imgFileName();
			setSavePath("/picture");
			FileOutputStream fos = new FileOutputStream(getSavePath()+"\\"+now_showingFileName);//getShowimgFileName()´̎»׃Ϊтτ¼�֣¬Ж՚ԃµŊȔ­ʏ´«τ¼�
			FileInputStream fis = new FileInputStream(getProject_img());
			
			 		byte[] buffer = new byte[1024];
			 		int len=0;
			 		while ((len=fis.read(buffer))>0)
			 		{
			 			 fos.write(buffer, 0, len);
			 		}
			 		buffer=null;
			 		fos.close();
			 		fis.close();
			 new FunProjectData().uploadProjectImage(project_id,getSavePath()+"/"+now_showingFileName);
			 //new FunProjectData().uploadProjectName(project_id,this.project_title);
			 //new FunProjectData().uploadProjectClassify(project_id,this.project_type);
			 //new FunProjectData().uploadLeader(project_id,this.main_partner);
			 //new FunProjectData().uploadProjectMembers(project_id,this.other_partner);
			 new FunProjectData().uploadIntroduction(project_id,this.project_introduction);
			 try{HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out=response.getWriter();
     out.write("success");}
       catch(Exception e)  {
	    System.out.println("error");}
  		return null;
   }

}