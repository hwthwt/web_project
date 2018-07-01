<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>优学堂精品课程管理系统登录</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    $(function(){
    	var i = 1;
    	$("#a").click(function(){
    		$("#img").attr("src","${pageContext.request.contextPath }/user/getCodeImage.do?num="+(i++));
    	});
    });
    </script>
  </head>
  <body>
  	<!-- 使用自定义css样式 div-signin 完成元素居中-->
    <div class="containercc div-signin" style="position: relative; top: 50%; margin-top: -215px;">
      <div class="panel panel-primary div-shadow">
      	<!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
	    <div class="panel-heading">
	    	<h3> 优学堂精品课程管理系统</h3>
	    	<span>High-quality Courses Manager System</span>
	    </div>
	    <div class="panel-body">
	      <!-- login form start -->
	      <form action="${pageContext.request.contextPath }/user/backLogin.do" class="form-horizontal ccc" method="post">
		    <div class="form-group">
		       <label class="col-xs-3 control-label">用户名：</label>
		       <div class="col-xs-9">
		         <input class="form-control" type="text" name="loginName" placeholder="请输入用户名">
		       </div>
		    </div>
		    <div class="form-group">
		       <label class="col-xs-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
		       <div class="col-xs-9">
		         <input class="form-control" name="password2" type="password" placeholder="请输入密码">
		       </div>
		    </div>
		    <div class="form-group">
		       <label class="col-xs-3 control-label">验证码：</label>
		       <div class="col-xs-4">
		         <input class="form-control" name="code" type="text" placeholder="请输入验证码">
		       </div>
		       <div class="col-xs-2">
		       	  <!-- 验证码图片加载（需引入验证码文件）图像高度经过测试，建议不要修改 -->
			      <img class="img-rounded" id="img" src="${pageContext.request.contextPath }/user/getCodeImage.do" alt="验证码" style="height: 32px; width: 70px;"/>
		       </div>
		       <div class="col-xs-2">
		         <button type="button" class="btn btn-link" id="a">看不清</button>
		       </div>
		       <span style="color: red;">${backLogin }</span>
		    </div>
		    <br/>
		    <div class="form-group">
		       <div class="col-xs-9  col-xs-offset-3 padding-left-0">
		       	 <div class="col-xs-6">
			         <button type="reset" class="btn btn-primary btn-block">重&nbsp;&nbsp;置</button>
		       	 </div>
		       	 <div class="col-xs-6">
			         <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;录</button>
		       	 </div>
		       </div>
		    </div>
	      </form>
	      <!-- login form end -->
	    </div>
	  </div>
    </div>
    <!-- 页尾 版权声明 -->
    <div class="containercc" style="position: relative;top: 50%;" >
		<div class="col-xs-12 foot-css" id="ccc" >
		        <p class="text-muted credit">
		            Copyright © 2017 南京网博 版权所有
		        </p>
	    </div>
    </div>
  </body>
</html>
