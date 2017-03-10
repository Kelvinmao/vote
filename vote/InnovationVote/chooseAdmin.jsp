<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*, db.fun.*,db.pojo.*,struts.UserVote.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />。
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>管理员功能选择</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!-- siimple style -->
    <link href="css/style.css" rel="stylesheet">
        <style type="text/css">
    body {
        background-color: #f6f6f6;
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
    	<a href="loginAdmin.jsp">重新登录</a>
      <% }  else{%>
     
    </div>
    
    <div id="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-4"> <img src="images/bg.jpg" height="500px" width="750px" float="right" /></div>
                <div class="col-lg-4 col-lg-offset-4">
                    <h1>管理员功能选择</h1>
                    <h2 class="subtitle">Vote for InnovationCamp</h2>
                    <br/>
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="window.location.href='loginwithcard.jsp'">刷卡登陆</button>
                    <br/>
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="window.location.href='loginwithoutcard.html'">无卡登陆</button>
                    <br/>
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="window.location.href='loginuser.jsp'">删除选票</button>
                    <br/>
                    <button type="button" class="btn btn-info btn-block btn-lg" onclick="window.location.href='lottery.jsp'">抽奖</button>
                </div>
            </div>
        </div>
    </div>
    <% }  %>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
</body>

</html>
