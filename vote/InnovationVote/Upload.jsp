<html l<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<title>�ϴ�ͼƬ </title>
</head>
<body>
	
<Center>
	  <FONT color="red">
                 <s:fielderror />
                  </FONT>
	
 ��ѡ����Ҫ�ϴ���ͼƬ <br>
<form action="upload" method="post" enctype="multipart/form-data" >
	            <label for="submit-project-id">��ĿID</label>
              <input type="text" id="submit-project-id" class="form-control" name="project_id" required>
              
              <label for="submit-project-title">��Ŀ����</label>
              <input type="text" id="submit-project-title" class="form-control" name="project_title" aria-describedby="submit-project-title-help" required>
              <span id="submit-project-title-help" class="help-block">��Ҫ�ύ��������Ŀ����,�����п��ܻ�Ӱ����ʾ����</span>
              
              <label for="submit-type">��Ŀ���</label>
              <select id="submit-type" class="form-control" name="project_type">
                <option value="����Ӳ��"����Ӳ��</option>
                <option value="������+">������+</option>
                <option value="�ǻ�����">�ǻ�����</option>
                <option value="����">����</option>
                <option value="����">����</option>
                <option value="���">���</option>
                <option value="����">����</option>
                <option value="����">����</option>
                <option value="ͨ��">ͨ��</option>
                <option value="ģʽʶ�������ѧϰ">ģʽʶ�������ѧϰ</option>
                <option value="����">����</option>
              </select>
              
               <label for="submit-main-partner">��Ŀ��Ҫ������</label>
              <input type="text" id="submit-main-partner" class="form-control" name="main_partner" aria-describedby="submit-main-partner-help" required>
              <span id="submit-other-partner-help" class="help-block">������������С��</span>
           
            <!--submit-other-partner-->
           
              <label for="submit-other-partner">��Ŀ������Ա</label>
              <input type="text" id="submit-other-partner" class="form-control"  name="other_partner" aria-describedby="submit-other-partner-help" required>
               <span id="submit-other-partner-help" class="help-block">����������С����С�죬С��</span>
          
            <!--submit-img-->
           
              <label for="submit-img">�ϴ���Ƭ</label>
              <input type="file" id="submit-img" name="project_img">
              <p class="help-block">��Ҫ�ϴ��������Ƭ���ϴ�jpg/jpeg/png��ʽ��Ƭ</p>
           
          
              <label for="submit-project-introduction">��Ŀ���</label>
              <textarea id="submit-project-introduction" class="form-control" name="project_introduction" rows="5" required maxlength="200" aria-describedby="submit-project-introduction-help" style="resize: none;"></textarea>
              <span id="submit-project-introduction-help" class="help-block">��д��Ŀ���ܣ�150~200�� </span>
              <input type="submit" name="Submit">
         
</form>
</body>
</html>