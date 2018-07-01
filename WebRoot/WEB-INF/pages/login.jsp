<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML >
<html>
	<head>
		<title>login</title>
		<script type="text/javascript">
			function putForm(){
				//$("#myForm").serialize();
				$("#loginForm").submit();
			}
			function checkFun(){
				//验证
				//$("#loginForm").submit();
				//var form = $("#myForm").serialize();
				var name = $("name").val();
				var pwd = $("pwd").val();
				var data = "username=" + name + "&password=" + pwd;
				//var data = {"username":name, "password":pwd};
				window.location.href="url" + data;
				/*
				$.ajax({
					"type": "post",
					"dataType" : "json",
					"url" : "",
					"data" : data,
					"success" : function(data){
						console.log(data.id+data.username);
					},
					"error" : function (){
						
					}
				});
				*/
				
			}
		</script>
	</head>
	<body>
		<h1>用户登录</h1>
		<hr/>
		<form id="loginForm" action="${pageContext.request.contextPath }/user/login.do" method="post">
			用户名：<input type="text" id="name" name="username"/><br/>
			密码：<input type="password" id="pwd" name="password"/><br/>
			<input type="submit" value="登录"/><br/>
			<!-- <input type="button" onclick="checkFun()" value="登录"/><br/> -->
			<a href="${pageContext.request.contextPath }/user/showRegist.do" >go to regist.</a>
		</form>
	</body>
</html>
