OCTYPE html>
<%@ page language="java" import="java.util.*, db.fun.*,db.pojo.*,struts.UserVote.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>大创展投票系统</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!-- siimple style -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        * {
            font-family: "Microsoft YaHei"
        }
        
        body {
            background-color: #f6f6f6;
        }
        
        .pro-image {
            width: 20px;
            height: 20px;
        }
        
        #logo-text,
        #login {
            color: #ffffff;
            font-size: 20px;
        }
        
        #logo {
            padding-top: 5px;
        }
    </style>
</head>

<body>
    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <!--           <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button> -->
                <a class="navbar-brand" id="logo" href="index.jsp"><img src="images/logo.png" weight="48px" height="48px" /></a>
                <p class="navbar-text" id="logo-text">InnovationCamp</p>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="loginAdmin.jsp" id="login">login in</a></li>
                </ul>
            </div>
        </div>
    </div>
    <% if(session.getAttribute( "username") == null ){ %>
    <div class="col-lg-4 col-lg-offset-4">
    	<h1>管理员未登录</h1>
    	<a href="loginAdmin.jsp" >重新登录</a>
      <% }   else{String Username=session.getAttribute("username").toString();String 	UserNo=session.getAttribute("UserNo").toString();
      	out.print(Username);out.print(UserNo);%>
    </div>
    <div id="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            项目名称<span class="label"></span>
                        </div>
                        <div class="panel-body">
                        		<form action="DeleteVote"  method="post" >
                        				<input type="hidden" name="UserNo" value="<%=UserNo %>" />用户名：  <%=UserNo %>  
                            		<table class="table table-hover">
                            				<tr>
                            						<th></th>
                                        <th>项目编号</th>
                                        <th>项目名称</th>
                                        <th>当前票数</th>
                                    </tr>
                                    <% List list=new FunUserData().userVote(UserNo);	
                                    	 VoteRecord[] v = new VoteRecord[list.size()]; 	
                                    	 Long[] l = new Long[list.size()];
                                    	 ProjectInfo[] p = new ProjectInfo[list.size()];%>
                                    <% for(int i=0; i<list.size();i++) { %>
                                   			<tr>
                                        		<td>
                                        				<img class="img-circle pro_image" src="images/user_face.jpg" width="35px" height="35px" />
                                        		</td>
                                            <td>
                                                <% v[i] = (VoteRecord)list.get(i); l[i] = v[i].getProjectID();%>  <%= l[i] %>
                                            </td>
                                            <td>
                                                <a href="project.action?projectID=<%=l[i] %>">
                                                <% p[i] = new FunProjectInfo().projectInfo(l[i]); %>
                                                <%= p[i].getProjectName()%>
                                                
                                                </a>
                                                
                                            </td>
                                            <td>
                                                <%= p[i].getVoteNum() %>
                                                <input type="radio" name="ProjectID" value="<%=((VoteRecord)list.get(i)).getProjectID() %>" check="checked" />
                                            </td>
                                               
                                            </tr>
                                    <% } %>
                                </table>
                                </br>
                                </br>
                                
                                <div class="form-group">
                    <div class="col-lg-10">
                        <div class="control-wrapper col-md-offset-1">
                            <s:fielderror/>
                        </div>
                    </div>
                </div>
                                </br>
                                </br>
                                 <td class="text-center">
                                               <input  class="btn btn-primary btn-block" type="submit" value="确认删除" />
                                                </td>
                          </form>
                
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">大创展投票系统</div>
                        <div class="panel-body">
                            <div class="row"><img id="pro_logo" src="images/user_face.jpg" class="center-block img-circle" alt="Image"></div>
                            <br/>
                            <div class="text-center">
                                <p>
                                    <h4>用户名：<%=new FunUserData().userInfo(UserNo).getName() %></h4></p>
                                <p>
                                    <h4>学号：<%=new FunUserData().userInfo(UserNo).getOutID() %></h4></p>
                            </div>
                            <br/>
                            <form class="form-inline signup" role="form" action="projectSearch" method="get">
                                <div class="input-group">
                                	  <input type="text" class="form-control" placeholder="搜索项目" name="search">
                                    <span class="input-group-btn">
                                <button type="submit" class="btn btn-info">走你</button>
                            </span>
                                </div>
                            </form>
                            <form action="Loginout" method="post">
                            		<input type="hidden" name="username" value="<%=Username %>" />
                            		<input type="hidden" name="UserNo" value="<%=UserNo %>" />
                                <input  class="btn btn-primary btn-block" type="submit" value="退出" />
                          	</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% }  %>
</body>

</html>
