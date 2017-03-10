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

public class ShowProjectInfoSearchAction extends ActionSupport
{
    
    private int page;
    private String search = "";
    private int Judge =1;
     public int getJudge()
    {
        return Judge;
    }

    public void setJudge(int Judge)
    {
        this.Judge = Judge;
    }
    
    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }
    
    public String getSearch()
    {
        return search;
    }

    public void setSearch(String search)
    {
        this.search = search;
    }


    public String execute() throws Exception
    {
    	 
    	  PageBean pageBean=null;
    	  String Classify=null;
    	  if(ActionContext.getContext().getSession().get("Classify")==null||Judge==0)
    	  {pageBean=new ProjectInfoSearch().getPageBean(12, page, search);
    	  	Judge=1;
    	  	ActionContext.getContext().getSession().put("Classify",null);
    	  	}
    	  else
    	  {Classify=ActionContext.getContext().getSession().get("Classify").toString();
    	  pageBean=new ClassifySelectAction().getSelectPageBean(12,page,Classify);}
        System.out.print(Classify);
        
        HttpServletRequest request = ServletActionContext.getRequest();
        
        request.setAttribute("pageBean", pageBean);
        
        return "success";
    }
}