﻿<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
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
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
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
    <script type="text/javascript">
        function hahaha() {
            alert("hahahha")
        }

        function cleartxt() {
            input = document.getElementsByTagName('cardSource');
            input.value = "";
        }
    </script>
</head>

<body onload="cleartxt()" class="templatemo-bg-gray">
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
    <br/>
    <br/>
    <br/>
    <div class="container">
        <br/>
        <br/>
        <h1 class="margin-bottom-15">大创展投票系统(刷卡登录)</h1>
        
        <form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" action="loginWithCard" method="post">
            <div class="form-group">
                <div class="col-lg-12">
                    <div class="control-wrapper">
                        <label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
                        <input type="password" id="cardSource" autofocus="true" type="text" class="form-control" id="username" placeholder="卡号" name="cardSource">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-8">
                    <div class="control-wrapper col-md-offset-5">
                        <s:fielderror/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-8">
                    <div class="control-wrapper col-md-offset-5">
                        <input type="submit" value="登录" class="btn btn-info btn-block">
                    </div>
                </div>
            </div>
            <hr/>
            <div class="form-group">
                <div class="col-md-12">
                    <div class="text-center">
                        <a href="loginwithoutcard.html" class="templatemo-create-new">Forget My Card<i class="fa fa-arrow-circle-o-right"></i></a>
                    </div>
                </div>
            </div>
        </form>
    </div>
    </div>
</body>

</html>
