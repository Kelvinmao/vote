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
import java.io.PrintWriter;

public class ClassifySelectAction extends ActionSupport
{
    
    private String Classify;
    private int page;

		public void setClassify(String Classify)
		{
			this.Classify = Classify;
		}
		 
		public String getClassify()
		{
		return this.Classify;
		}
		public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }	
		
		 public PageBean getSelectPageBean(int pageSize, int page, String Classify)
    {
    	 
        PageBean pageBean = new PageBean();
        
        List temp = new FunProjectData().ProjectDataClassifySearch(Classify);
        
        int allRows = temp.size();
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        List list = new FunProjectData().ProjectDataClassifySearch((currentPage-1)*pageSize,pageSize,Classify);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
      
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        
        return pageBean;
    }
 
	public String execute()
	{
		 //Long l =  Long.parseLong(projectID);
		 String judge="";
		 switch (Classify)
		 {
		 	case "1": judge="����Ӳ��";break;
		 	case "2": judge="����";break;
		 	case "3": judge="����";break;
		 	case "4": judge="���";break;
		 	case "5": judge="����";break;
		 	case "6": judge="����";break;
		 	case "7": judge="ͨ��";break;
		 	case "8": judge="ģʽʶ�������ѧϰ";break;
		 	}
		 	
		 ActionContext.getContext().getSession().put("Classify",judge);
		 PageBean pagebean=getSelectPageBean(12,page,judge);
		 HttpServletRequest request = ServletActionContext.getRequest(); 
      request.setAttribute("pageBean", pagebean);
 
		 return  "success";
    }
}