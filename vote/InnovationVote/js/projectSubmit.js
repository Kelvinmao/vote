//var CAPTCHA=false;
/*$.ajax({
    url: 'http://g.cn',
    dataType:'json'
    }).done(function(data, status, xhr){
    	if(data.haveSubmit) {
    		$("#submit-project").addClass("btn-danger");
    		$("#submit-project").text("修改");
    		$(".submit-info").addClass("bg-danger");
    		$(".submit-info").find("p").text("你已经提交过，还可以进行修改，请不要提交相同的内容，请使用chrome7+，IE10+，Safari5+或其他浏览器高速模式提交,否则会导致提交失败，请注意")
    	}
});*/

/*$("#submit-CAPTCHA").blur(function(){
	$.ajax({
    url: 'http://g.cn',
    dataType:'json'
    }).done(function(data, status, xhr){
    	if(data.CAPTCHA) {
    		$(".submit-CAPTCHA-text").removeClass("result-error");
    		$(".submit-CAPTCHA-text").addClass("result-success");
    		$(".submit-CAPTCHA-text").text("验证码正确");
    		CAPTCHA=true;
    	}
    	else {
    		$(".submit-CAPTCHA-text").addClass("result-error");
    		$(".submit-CAPTCHA-text").text("验证码错误");
    		CAPTCHA=false;
    	}
});
})
*/

$("#submit-project").click(function() {
	var img = document.getElementById("submit-img").files[0];//获取图片信息
	if($("#submit-project-title").val()=="" || $("#submit-main-partner").val()=="" || $("#submit-other-partner").val()=="" || $("#submit-project-introduction").val()=="" || img==null) {
		$(".modal-text").find("p").addClass("result-error");
		$(".modal-text").find("p").removeClass("result-success");
		$(".modal-text").find("p").html("<span class='glyphicon glyphicon-remove-sign'><span>&nbsp;项目信息不完整，请检查是否填写完整");
		$('#submit-result').modal('show');
		return false;
	}
	var img_type = img.type;
	var img_test = /jpg|jpeg|png/g;
	if(!img_test.test(img_type)) {
		$(".modal-text").find("p").addClass("result-error");
		$(".modal-text").find("p").removeClass("result-success");
		$(".modal-text").find("p").html("<span class='glyphicon glyphicon-remove-sign'><span>&nbsp;请选择正确格式图片提交");
		$('#submit-result').modal('show');
		return false;
	}
	 var formData = new FormData($("#submit-form")[0]);
	 $.ajax({
	    url: 'upload.action',
	    type: 'post',
	    data: formData,
	  // dataType: 'JSON',
		async: false,
		cache: false,
		contentType: false,
		processData: false

	    }).done(function(data, status, xhr){
	    	if(data=="success") {
	    		$(".modal-text").find("p").removeClass("result-error");
	    		$(".modal-text").find("p").addClass("result-success");
	    		$(".modal-text").find("p").html("<span class='glyphicon glyphicon-ok-sign'><span>&nbsp;提交成功");
	    		$('#submit-result').modal('show');
	    	}
	    	else {
	    		$(".modal-text").find("p").addClass("result-error");
	    		$(".modal-text").find("p").removeClass("result-success");
	    		$(".modal-text").find("p").html("<span class='glyphicon glyphicon-remove-sign'><span>&nbsp;提交错误，请检查一下图片大小（小于1M可行），重新提交");
	    		$('#submit-result').modal('show');
	    	}
	    }).fail(function(){
	    	$(".modal-text").find("p").addClass("result-error");
	    	$(".modal-text").find("p").removeClass("result-success");
	    	$(".modal-text").find("p").html("<span class='glyphicon glyphicon-remove-sign'><span>提交失败，有可能是网络原因引起");
	    	$('#submit-result').modal('show');
	    });

});