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

public class AddressAction extends ActionSupport
{
    private String address;
    private String username;
    private String phone;
    public String getUsername()
    {
      return this.username;
    }
    public void setUsername(String username)
    {
     this.username=username;
    }
    public String getAddress()
    {
      return this.address;
    }
    public void setAddress(String address)
    {
     this.address=address;
    }
      public String getPhone()
    {
      return this.phone;
    }
    public void setPhone(String phone)
    {
     this.phone=phone;
    }
	  public String execute() 
		{
		String userid=ActionContext.getContext().getSession().get("userid").toString();	
		String prize1=ActionContext.getContext().getSession().get("prize").toString();
		Long prize=Long.parseLong(prize1);
		new FunLotteryData().getWinner(userid,prize,username,address,phone); 
    try{HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out=response.getWriter();
     out.write("success");}
       catch(Exception e)  {
	    System.out.println("error");}
  		return null;
  	}
}