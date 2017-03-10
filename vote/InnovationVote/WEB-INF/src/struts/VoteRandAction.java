package struts;

import db.fun.*;
import db.pojo.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.String.*;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VoteRandAction extends ActionSupport
{
	  public String execute() 
		{
		  String[] TopGroups=new  String[10];
      Long[] VoteNumbers=new Long[10];
		  List list=new FunProjectData().showProjectVote();
		for(int i=0;i<10;i++)
		{
			ProjectData p=(ProjectData)list.get(i);
			TopGroups[i]=p.getProjectName();
			VoteNumbers[i]=p.getVoteNumber();
			System.out.println(TopGroups[i]);
			System.out.print("*******************");
		}
		System.out.print(VoteNumbers.toString());
    //String NumbersJson = gson.toJson(VoteNumbers);
		//String GroupsJson = gson.toJson(TopGrpups);
		Map<String, Long> data = new HashMap<String, Long>();
		for(int i=0;i<10;i++)
		{
			data.put(TopGroups[i],VoteNumbers[i]);
			}
    JSONObject json = JSONObject.fromObject(data);;
    System.out.print(json);
    try{HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out=response.getWriter();
     out.write(json.toString());}
       catch(Exception e)  {
	    System.out.println("error");}
  		return null;
  	}
}