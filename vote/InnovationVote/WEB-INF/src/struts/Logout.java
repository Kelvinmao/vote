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
public class Logout extends ActionSupport
{
	public String execute() throws Exception
	{
	ActionContext.getContext().getSession().put("project_id","");
	return "success";
  }
}