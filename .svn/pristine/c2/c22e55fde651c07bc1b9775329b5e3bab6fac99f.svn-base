<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<!-- <script type="text/javascript">
	function ajaxTest() {
		//var user = $("#registForm").serializeArray();
		//有 form时的数据处理方式
		//var formData = new FormData($("#registForm")[0]);
		
		var formData = new FormData();//ajax上传文件时数据的类型
		var f = $('#myFile')[0].files[0];
		console.log(f);
		//没有form时 数据的处理方式
		formData.append('file', f);
		//formData.append('username', $('input[name=nickname]').val());
		
/* 		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/user/ajaxRegist.do",
			dataType : "json",
			data: formData,
			processData: false,//ajax上传文件必须的属性1
			contentType: false,//ajax上传文件必须的属性2
			success : function(data) {
				alert(data);
			},
			error : function(){
				alert("error");
			}
		}); */
		
	}
	$(function () {
		$('#imageCode').click(function(){
			var url = $(this).prop('src') + "&time=" + new Date().getTime(); 
			$('#imageCode').prop('src', url);
		});
		$('#codeInput').blur(function(){
			var userCode = $(this).val();
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/user/getCodeStr.do",
				dataType : "json",
				success : function(data) {
					console.log(data);
					if(userCode != data.obj){
						alert('验证码错误!');
					}else{
						alert('验证码正确!');
					}
				},
				error : function(){
					alert("error");
				}
			});
		});
	});
</script> -->
</head>
<body>
	<h1>用户注册</h1>
	<hr />
	<form id="registForm"
		action="${pageContext.request.contextPath }/user/regist.do"
		method="post" enctype="multipart/form-data">
		用户名：<input type="text" name="username" />
		<br />
		昵称：<input type="text" name="nickname" />
		<br />
		角色：<input type="text" name="role" />
		<br />
		密码：<input type="password" name="password" />
		<br />
		邮箱：<input type="text" name="email" />
		<br />
		图片:<input type="file" id="myFile" name="file" />
		<br />
		验证码:<input type="text" name="imageCode" id="codeInput" />
		<img id="imageCode" src="${pageContext.request.contextPath }/user/getCodeImage.do?codeStr=a9x7&num=1" alt="no image code" />
		<br />
		<input type="submit" value="确认and注册" /><br/>
		<a href="${pageContext.request.contextPath }/user/showLogin.do" >go to login.</a>
	</form>
	<div style="color: red;">${msg}</div>
	<div>
		<img src="${fileDir}${imageUrl}" alt="no image" />
	</div>
</body>
<button onclick="ajaxTest();">ajax</button>
</html>
