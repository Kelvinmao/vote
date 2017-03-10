package struts;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;  
import net.sf.json.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  
import  db.fun.*;
import db.pojo.*;

public class JsonAction extends ActionSupport {

    //private static final long serialVersionUID = 1L;    
   //private HttpServletRequest request;
    public String mydata;    //用于返回json 的数据结果，必须有getter and setter方法
    public JSONObject json1;
    String[] name=new String[10];	
    int[] votenum=new int[10];	
    
  /*  public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }*/
    
 //获取数据   
    public void getalldata(){  
    	         List l=new FunProjectInfo().showProjectVote(0,10); 
    	       	ProjectInfo[] p = new ProjectInfo[10];
    	         for(int i=0; i<10; i++)
              {
              p[i] = (ProjectInfo)l.get(i);
			name[i]= p[i].getProjectName();
			votenum[i]= p[i].getVoteNum();
			 }	 
    	}
    	
 //将数据存储在map里，再转换成json类型数据
    	public JSONObject changetomap(){
    		   Map<String,Object> map = new HashMap<String,Object>();
    		   for(int i=0;i<10;i++){
            map.put("name", name[i]);
            map.put("votenum",votenum[i]);  }
            
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            return json;
          }
          
    public String excuteAjax(){
        try {
           getalldata();
           json1=changetomap();
           mydata = json1.toString();//给data赋值，传递给页面  有问题
          } 
        catch (Exception e) {
            e.printStackTrace();
          }
        return "success";
      }   
}       




 