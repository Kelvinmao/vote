<%@ page language="java" import="java.util.*, db.fun.*,db.pojo.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="struts.PageBean" %>
<html lang="en">

<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        %>
        
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <base href="<%=basePath%>">
    <meta charset="GBK">
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

    a{
        font
    }
    </style>
        <script type="text/javascript">
    
        function validate()
        {
            var page = document.getElementsByName("page")[0].value;
                
            if(page > <s:property value="#request.pageBean.totalPage"/>)
            {
                alert("你输入的页数大于最大页数，页面将跳转到首页！");
                
                window.document.location.href = "showProjectInfoSearchAction";
                
                return false;
            }
            
            return true;
        }
    </script>
</head>

<body>
    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" id="logo" href="index.html"><img src="images/logo.png" weight="48px" height="48px" /></a>
                <p class="navbar-text" id="logo-text">InnovationCamp</p>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.html" id="login">login in</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-9">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Search
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <tr>
                                    <th>logo</th>
                                    <th>编号</th>
                                    <th>项目名称</th>
                                    <th>票数</th>
                                </tr>
                                <!-- 循环显示列表 -->
                                <s:iterator value="#request.pageBean.list" id="project" status="it">
                                <s:if test="%{#it.getCount==1}">
                                      <tr>
                                        <td></td>
                                        <td></td>
                                        <td>
                                           对不起，找不到你想要的东西
                                        </td>
                                        <td></td>        
                                    </tr>
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td><img class="img-circle pro_image" src="images/user_face.jpg" width="35px" height="35px" /></td>
                                        <td><s:property value="#project.projectID"/></td>
                                        <td>
                                            <a href="project.action?projectID=<s:property value="#project.projectID"/>" >
                                                <s:property value="#project.projectName"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#project.voteNum"/></td>        
                                    </tr>
                                    </s:else>
                                </s:iterator>
                            </table>
                            <!-- 分页 -->
                            <nav class="text-center">
                                <ul class="pagination">
                                    <li>
                                        <a href="projectSreach.action?page=<s:property value="#request.pageBean.currentPage - 1"/>" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <% 
                                        PageBean page1=(PageBean)request.getAttribute("pageBean");
                                        int totalpage=page1.getTotalPage();
                                        int currentPage=page1.getCurrentPage();
                                        for (int i=1;i<=totalpage;i++)
                                            {
                                                if (currentPage==i) 
                                                {
                                                    out.println("<li class=\"active\"><a href=\"projectSearch.action?page="+i+"\" >"+i+"</a></li>");
                                                }
                                                else
                                                {
                                                    out.println("<li><a href=\"projectSearch.action?page="+i+"\" >"+i+"</a></li>");
                                                }
                                            }
                                    %>
                                    <li>
                                         <a href="projectSearch.action?page=<s:property value="#request.pageBean.currentPage + 1"/>" aria-label="Next">
                                         <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">大创展投票系统</div>
                        <div class="panel-body">
                    <form class="form-inline signup" role="form" action="projectSearch" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="搜索项目" name="search">
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-info">走你</button>
                            </span>
                        </div>
                    </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
