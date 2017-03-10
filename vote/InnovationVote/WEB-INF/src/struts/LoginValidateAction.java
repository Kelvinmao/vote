package struts;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginValidateAction extends ActionSupport
{
    private String CheckCode;
    private String UserNo;				//�û���
	  private String UserPwd;				//����
	  private InputStream inputStream;
   public InputStream getInputStream() 
   {
        return inputStream;
   }
   public void setInputStream(InputStream inputStream) 
   {
        this.inputStream = inputStream;
   }
   public void setUserNo(String UserNo)
	 {
		this.UserNo = UserNo;
	 }
	
	 public String getUserNo()
	 {
		return this.UserNo;
   }
	
	 public void setUserPwd(String UserPwd)
	 {
		this.UserPwd = UserPwd;
	 }
	
	 public String getUserPwd(String UserPwd )
	 {
		return this.UserPwd;
	 }
   public String getCheckCode()
   {
    return CheckCode;
   }
   public void setCheckCode(String CheckCode)
   {
        this.CheckCode = CheckCode;
   }

    @Override
    public String execute() throws Exception
    {
    	  if(validate()=="SUCCESS")
        inputStream = new StringBufferInputStream("SUCCESS");
    }
    
    @Override
    public String validate()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String CheckCode2 = (String)session.getAttribute("CheckCode");
        
        if(!CheckCode.equals(CheckCode2))
        {
            this.addActionError("�������֤�벻��ȷ�����������룡");
            return WRONG;
        }
        else return SUCCESS;
    }
}