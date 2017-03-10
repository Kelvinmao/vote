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

public class ShowProjectOnlyAction extends ActionSupport
{
    
    private ProjectInfo project;
    private Long projectID;

    
    public ProjectInfo getProject()
    {
        return project;
    }

    public void setProject(ProjectInfo project)
    {
        this.project = project;
    }


	
		
		public void setProjectID(Long projectID)
		{
			this.projectID = projectID;
		}
		 
		public Long getProjectID()
		{
		return this.projectID;
		}
			
		
		
 
	public String execute()
	{
		 project = new FunProjectInfo().showProjectOnly(projectID);
		 
		 return  "success";
    }
}