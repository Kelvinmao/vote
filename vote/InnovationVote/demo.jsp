<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>大创展投票系统</title>
  <!-- Bootstrap -->
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/bootstrap-theme.css" rel="stylesheet">
  <link href="css/vegas.min.css" rel="stylesheet">
  <!-- simple style -->
  <link href="css/style.css" rel="stylesheet">
  <style type="text/css">
  body {
    background-color: #f6f6f6;
  }
  
  #logo-text{
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
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" id="logo" href="index.jsp" style="margin-bottom: 5px"><img src="images/logo.png" weight="45px" height="45px" /></a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="#" onclick="window.location.href='projectSearch.action'">我要投票</a></li>
        <li><a href="#" data-toggle="modal" data-target="#lottery-modal">我要抽奖</a></li>
        <li><a href="#">项目列表</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search" action="projectSearch" method="POST">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="搜索项目">
          <button type="submit" class="btn btn-default">走你</button>
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right" style="margin-right: 20px">
        <div class="dropdown pull-right">
          <button class="btn dropdown-toggle" type="button" id="login-name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            未登录 
            <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
            <li><a href="loginAdmin.jsp" id="login">登录</a></li>
            <li class="disabled"><a href="logout.jsp">注销</a></li>
          </ul>
        </div>
      </ul>
    </div>
  </nav>

  <div id="main">
    <div class="container" style="">
      <div class="row main" style="margin-top: 100px">
        <div class="col-lg-4 col-lg-offset-8" style="text-align: center;">
          <img class="col-lg-12" src="images/WIN-2.png" id="WIN-2">
          <h2>第八届大创展</h2>
          <button type="button" class="btn btn-info btn-block btn-lg" onclick="window.location.href='projectSearch.action'">我要投票</button>
          <br/>
          <button type="button" class="btn btn-info btn-block btn-lg" onclick="window.location.href='voterand.jsp'">投票排名</button>
        </div>
      </div>
    </div>
  </div>

  <!--lottery-modal-->
  <div class="modal fade" id="lottery-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
            抽奖
         </h4>
        </div>
          <div class="modal-body" style="overflow: hidden">
            <div class="col-lg-12">
              <form id="form" class="form-horizontal" role="form">
                <div id="lottery-user" class="form-group">
                  <label class="col-sm-2 control-label">用户名</label>
                  <div class="col-sm-10">
                    <input class="textin form-control" type="text" placeholder="用户名">
                  </div>
                </div>
                <div id="lottery-psw" class="form-group">
                  <label class="col-sm-2 control-label">密码</label>
                  <div class="col-sm-10">
                    <input class="textin form-control" type="text" placeholder="密码">
                  </div>
                </div>
                <div id="lottery-check" class="form-group">
                  <label class="col-sm-2 control-label">验证码</label>
                  <div class="col-sm-7">
                    <input class="textin form-control" type="text" placeholder="验证码">
                  </div>
                  <div class="col-sm-3" id="check-img"><img src="images/win-2.png"></div>
                </div>
                <div class="col-sm-12 check-alert alert alert-danger" role="alert">
                  你的验证码输入错误！ 
                </div>
              </form>
            </div>
          </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭
          </button>
          <button type="button" class="btn btn-primary" data-toggle="modal">
            抽奖 
          </button>
        </div>
      </div>
    </div>
  </div>
  <!--lottery-result-->
  <div class="modal fade" id="lottery-result" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
            抽奖结果
         </h4>
        </div>
          <div class="modal-body" style="overflow: hidden">
            <div class="col-lg-12 success">
                <div>
                  恭喜你抽中<span id="lo-result"></span>等奖!
                </div>
                <label class="col-sm-4 address-lab">请填写收货地址：</label>
                <div class="col-sm-8 address">
                    <input class="textin form-control" type="text" placeholder="收货地址">
                </div>
                <div style="color: red;">
                  请认真填写，只有这一次机会！
                </div>
            </div>
            <div class="fail">
              很遗憾你没抽中，再接再厉~
            </div>
          </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default closeUp" data-dismiss="modal">关闭
          </button>
          <button type="button" class="btn btn-default lotteryUp">提交
          </button>
        </div>
      </div>
    </div>
  </div>

  <script src="js/jquery-1.11.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/vegas.min.js"></script>
  <script type="text/javascript" src="js/mock.js"></script>
  <script src="js/index.js"></script>
  <script type="text/javascript">
      $(document).ready(function(){$('body').vegas({
        slides: [
          { src: "images/BG1.png"},
          { src: "images/BG2.jpg"},
          { src: "images/BG3.jpg"},
          { src: "images/BG4.jpg"},
          { src: "images/BG5.jpg"},
          { src: "images/BG6.jpg"},
          { src: "images/BG7.jpg"},
        ],
        timer: false,
        transition: 'random',
        delay: 6000
      })
    })
  </script>
</body>
</html>
