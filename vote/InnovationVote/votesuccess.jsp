﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
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
    <div id="header">
        <div class="container">
            <div class="row">
                <center><h2>
<s:property value="UserNo"/>  投票成功!  您已投了<s:property value="VoteNum" />票！</br>
<button  class="btn btn-danger" onclick="window.history.back(-1)"><s:text name="返回投票页面" /></button>
            </div>
        </div>
    </div>
</body>


</html>