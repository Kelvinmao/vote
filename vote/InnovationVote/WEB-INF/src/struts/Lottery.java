package struts;

import db.fun.*;
import db.pojo.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  
import java.io.PrintWriter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lottery extends ActionSupport
{
	private String username;				//用户名
	private String password;
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return this.username;
  }
		public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return this.password;
  }
	
	public String execute() throws Exception
	{
		
    ActionContext.getContext().getSession().put("userid",this.username);
    System.out.println(username+"-------------------");
    System.out.println(password);
		if(!new FunUserData().checkUser(username,password))
	 	{
	 		String jsonString="{\"status\":\"logerror\",\"award\":0}";
			this.addFieldError(username,"学号输入错误");
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  		return null;
		}
		
		if(new FunLotteryData().ifCanDrawn(username))
		{
			String jsonString="{\"status\":\"enough\",\"award\":0}";
			this.addFieldError(username,"您没有抽奖机会！");
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  		return null;
	  }
	  else
	  if (new FunLotteryData().ifDrawn(username)!=0L) //???????
		{
			String jsonString="{\"status\":\"hassucceed\",\"award\":0}";
			this.addFieldError(username,"您已经中奖，请勿重复抽奖");
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  		return null;
	  }
	  
	  
	  this.addFieldError(username,"符合要求，可以抽奖");
		Boolean result=new FunUserData().doLottery(username);
		
		if(new FunLotteryData().ifDrawn(username)==0L)
		{ String jsonString="{\"status\":\"fail\",\"award\":0}";
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  	return null;}
		else
		if(new FunLotteryData().ifDrawn(username)==1L)
		{ String jsonString="{\"status\":\"success\",\"award\":1}";
  		ActionContext.getContext().getSession().put("prize",new FunLotteryData().ifDrawn(username));
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  	return null;}
		else
		if(new FunLotteryData().ifDrawn(username)==2L)
		{ String jsonString="{\"status\":\"success\",\"award\":2}";
			ActionContext.getContext().getSession().put("prize",new FunLotteryData().ifDrawn(username));
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  	return null;}
		else
		if(new FunLotteryData().ifDrawn(username)==3L)
		{ String jsonString="{\"status\":\"success\",\"award\":3}";
			ActionContext.getContext().getSession().put("prize",new FunLotteryData().ifDrawn(username));
			try{PrintWriter out=ServletActionContext.getResponse().getWriter();
     out.write(jsonString);}
       catch(Exception e)  {
	    System.out.println("发生异常，登陆失败！");}
  		return null;}
  		return null;

	}	
}
