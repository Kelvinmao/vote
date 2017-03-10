<html l<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<title>上传图片 </title>
</head>
<body>
	
<Center>
	  <FONT color="red">
                 <s:fielderror />
                  </FONT>
	
 请选择需要上传的图片 <br>
<form action="upload" method="post" enctype="multipart/form-data" >
	            <label for="submit-project-id">项目ID</label>
              <input type="text" id="submit-project-id" class="form-control" name="project_id" required>
              
              <label for="submit-project-title">项目标题</label>
              <input type="text" id="submit-project-title" class="form-control" name="project_title" aria-describedby="submit-project-title-help" required>
              <span id="submit-project-title-help" class="help-block">不要提交过长的项目标题,否则有可能会影响显示体验</span>
              
              <label for="submit-type">项目类别</label>
              <select id="submit-type" class="form-control" name="project_type">
                <option value="智能硬件"智能硬件</option>
                <option value="互联网+">互联网+</option>
                <option value="智慧生活">智慧生活</option>
                <option value="电子">电子</option>
                <option value="数理">数理</option>
                <option value="软件">软件</option>
                <option value="创意">创意</option>
                <option value="论文">论文</option>
                <option value="通信">通信</option>
                <option value="模式识别与机器学习">模式识别与机器学习</option>
                <option value="其他">其他</option>
              </select>
              
               <label for="submit-main-partner">项目主要负责人</label>
              <input type="text" id="submit-main-partner" class="form-control" name="main_partner" aria-describedby="submit-main-partner-help" required>
              <span id="submit-other-partner-help" class="help-block">输入样例：马小云</span>
           
            <!--submit-other-partner-->
           
              <label for="submit-other-partner">项目其他成员</label>
              <input type="text" id="submit-other-partner" class="form-control"  name="other_partner" aria-describedby="submit-other-partner-help" required>
               <span id="submit-other-partner-help" class="help-block">输入样例：小明，小红，小张</span>
          
            <!--submit-img-->
           
              <label for="submit-img">上传照片</label>
              <input type="file" id="submit-img" name="project_img">
              <p class="help-block">不要上传过大的照片，上传jpg/jpeg/png格式照片</p>
           
          
              <label for="submit-project-introduction">项目简介</label>
              <textarea id="submit-project-introduction" class="form-control" name="project_introduction" rows="5" required maxlength="200" aria-describedby="submit-project-introduction-help" style="resize: none;"></textarea>
              <span id="submit-project-introduction-help" class="help-block">填写项目介绍，150~200字 </span>
              <input type="submit" name="Submit">
         
</form>
</body>
</html>