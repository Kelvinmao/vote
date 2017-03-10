<html lang="en">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    
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
                <div class="col-lg-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            项目名称<span class="label">365</span>
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">
                                <img class="img-rounded" src="images/user_face.jpg" />
                            </div>
                            <div class="col-lg-4">
                                <h4>按项目号显示信息</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">大创展投票系统</div>
                        <div class="panel-body">
                            <div class="row"><img id="pro_logo" src="images/user_face.jpg" class="center-block img-circle" alt="Image"></div>
                            <br/>
                            <div class="">
                                <button class="btn btn-primary btn-block" type="button">
                                    给这个项号投票: <span class="badge">显示项目号</span>
                                </button>
                            </div>
                            <br/>
                            
                            
                            
                           
                                <div class="input-group">
                                    <s:form action="userlogin" method="post" >
                                 
                                
                                  

                                 
									                     <td> <s:textfield label="用户名" name="UserNo">用户名</td></s:textfield>
	                                      <td><input type="radio" name="ProjectID"  value=<%=1%>  check="checked"checked / >显示项目号</label></td>
  																						
																								
                                         < s:submit/><s:reset/>
                                	        </s:form>
                            </span>
                            
                                </div>
                           
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
