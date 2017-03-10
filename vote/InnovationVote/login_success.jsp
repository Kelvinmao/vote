<!DOCTYPE html>
<html>
	<head>
		<title>创新大本营创新项目投票系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			body{background-image:url("./bg/bg.jpg");}
		</style>
	</head>
	<body class="templatemo-bg-gray">
		<h1 class="margin-bottom-15">创新大本营创新项目投票系统</h1>
		<div   class="container">
			<div id="left" class="col-md-8 templatemo-container margin-bottom-20">
				<div class="row">
					<div class="col-md-4 text-right">
						<h2>投票状态</h2>
					</div>
					<div class="col-md-4 text-left">
						<h2>已投票数：</h2>
					</div>
					<div class="col-md-4 text-left">
						<h2>剩余票数：</h2>
					</div>
				</div>
				<div class="row">
					<table class="table table-hover">
						<tr>
							<td>编号</td>
							<td>项目名称</td>
							<td>操作</td>
						</tr>
						<tr>
							<td>1</td>
							<td>我是一个项目</td>
							<td><button type="button" class="btn btn-primary">删除</button></td>
						</tr>
						<tr>
							<td>2</td>
							<td>我是一个项目</td>
							<td><button type="button" class="btn btn-primary">删除</button></td>
						</tr>
						<tr>
							<td>3</td>
							<td>我是一个项目</td>
							<td><button type="button" class="btn btn-primary">删除</button></td>
						</tr>
						<tr>
							<td>4</td>
							<td>我是一个项目</td>
							<td><button type="button" class="btn btn-primary">删除</button></td>
						</tr>
						<tr>
							<td>5</td>
							<td>我是一个项目</td>
							<td><button type="button" class="btn btn-primary">删除</button></td>
						</tr>
						<tr>
							<td>6</td>
							<td>我是一个项目</td>
							<td><button type="button" class="btn btn-primary">删除</button></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="right" class="col-md-4 templatemo-container margin-bottom-30 center-block">
				<div class="row"><img src="./bg/user_face.jpg" class="center-block img-circle" alt="Image"></div>
				<div class="row text-center">
					<br>
					<p><h4>用户名：路人甲</h4></p>
					<p><h4>学号：2013210738</h4></p>
					<p><h4>注册时间：2014.4.29</h4></p>
					<p><h4>投票状态：有效</h4></p>
				</div>
			</div>
		</div>
	</div>
	<script>
	function test()
	{
	if($("#left").height() > $("#right").height())
	{
		$("#right").css("height",$("#left").height())
	}else
	{
		$("#left").css("height",$("#right").height())
	}
	}
	test()
	</script>
</body>
</html>