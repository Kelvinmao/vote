<%@ page language="java" import="java.util.*, db.fun.*,db.pojo.*,struts.UserVote.*" contentType="text/html; charset=GBK" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
        <html>


        <head>
            <% FunVoteRecord record=null; %>
        </head>
        <title> 删除界面 </title>
        <div align="center">
            <table width="600" border="1" cellpadding="0" cellspacing="0" bordercolor="#00CCFF">
                <tr>
                    <td height="20" align="center">
                        <form action="Loginout" method="post">
                            <% if(session.getAttribute( "username")==null || session.getAttribute( "username")=="" ){ %>
                                未登录， <a href="login2.jsp">登陆</a>
                                <% } else { String Username=session.getAttribute( "username").toString(); String UserNo=session.getAttribute( "UserNo").toString(); out.println(Username); out.println(UserNo); %>
                                    <input type="hidden" name="username" value="<%=Username %>" />
                                    <input type="hidden" name="UsreNo" value="<%=UserNo %>" />
                                    <input type="submit" value="退出" />
                        </form>
                        <form action="DeleteVote" method="post">
                            <% List list=new FunUserData().userVote(UserNo); %>
                                <% for(int i=0; i<list.size();i++) { %>
                                    <td>
                                        <input type="radio" name="ProjectID" value="<%=((VoteRecord)list.get(i)).getProjectID() %>" check="checked">
                                        <%=((VoteRecord)list.get(i)).getProjectID() %>
                                            </label>
                                    </td>

                                    <% } %>
                                        <input type="hidden" name="UserNo" value="<%=UserNo %>" />
                                        <%=UserNo %>
                                            <input type="submit" />
                       
                        <%} %>
 </form>



            </table>




        </div>

        </body>

        </html>
