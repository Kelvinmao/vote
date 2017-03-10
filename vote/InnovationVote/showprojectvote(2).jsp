<%@ page language="java" import="java.util.*, db.fun.*,db.pojo.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
  <head>
  	<%
  	String path = request.getContextPath();
  	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		%>
		
    <base href="<%=basePath%>">
    
    <title>��ʾ��ĿͶƱ��Ϣ</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!-- siimple style -->
    <link href="css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>


    <script type="text/javascript">
    
        function validate()
        {
            var page = document.getElementsByName("page")[0].value;
                
            if(page > <s:property value="#request.pageBean.totalPage"/>)
            {
                alert("�������ҳ���������ҳ����ҳ�潫��ת����ҳ��");
                
                window.document.location.href = "showProjectVoteAction";
                
                return false;
            }
            
            return true;
        }
    
    </script>

  </head>
  
  <body>

    
    
    <table class="table table-striped">
    	<thead>
    	  <tr>
            <th>���</th>
            <th>����</th>
            <th>����</th>
        </tr>
    	</thead>
    <tbody>
    <s:iterator value="#request.pageBean.list" id="project">
    
        <tr>
            <th><s:property value="#project.projectID"/></th>
            <th><s:property value="#project.projectName"/></th>
            <th><s:property value="#project.voteNum"/></th>        
        </tr>
    </s:iterator>
    </tbody>
    </table>
    
    <center>
    
        <font size="15">��<font color="red"><s:property value="#request.pageBean.totalPage"/></font>ҳ </font>&nbsp;&nbsp;
        <font size="15">��<font color="red"><s:property value="#request.pageBean.allRows"/></font>����¼</font><br><br>
        
        <s:if test="#request.pageBean.currentPage == 1">
            ��ҳ&nbsp;&nbsp;&nbsp;��һҳ
        </s:if>
        
        <s:else>
            <a href="showProjectVoteAction.action">��ҳ</a>
            &nbsp;&nbsp;&nbsp;
            <a href="showProjectVoteAction.action?page=<s:property value="#request.pageBean.currentPage - 1"/>">��һҳ</a>
        </s:else>
        
        <s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage">
            <a href="showProjectVoteAction.action?page=<s:property value="#request.pageBean.currentPage + 1"/>">��һҳ</a>
            &nbsp;&nbsp;&nbsp;
            <a href="showProjectVoteAction.action?page=<s:property value="#request.pageBean.totalPage"/>">βҳ</a>
        </s:if>
        
        <s:else>
            ��һҳ&nbsp;&nbsp;&nbsp;βҳ
        </s:else>
    
    </center><br>
    
    <center>
        
        <form action="showProjectVoteAction" onsubmit="return validate();">
            <font size="4">��ת��</font>
            <input type="text" size="2" name="page">ҳ
            <input type="submit" value="��ת">
        </form>
        
    </center>
    
  </body>
</html>