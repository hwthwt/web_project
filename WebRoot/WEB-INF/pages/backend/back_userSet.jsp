<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/nmms.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/back-index.css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$(".doModify").on("click", function() {
			$(".modal-title").html("用户修改");
			$("#myModal").modal("show");
		});
		$(".updateOne").on("click", function() {
			$("#myModal").modal("hide");
		});

		// 显示隐藏查询列表
		$('#show-user-search').click(function() {
			$('#show-user-search').hide();
			$('#upp-user-search').show();
			$('.showusersearch').slideDown(500);
		});
		$('#upp-user-search').click(function() {
			$('#show-user-search').show();
			$('#upp-user-search').hide();
			$('.showusersearch').slideUp(500);
		});
		//同步分页
		//按钮的超链接设置函数
		//      myoptions.pageUrl = function(type, page, current) {
		//          return "${pageContext.request.contextPath }/findAll.do?pageNo="
		//                  + page;
		//      };
		//分页,在bootstrap-mypaginator.js中
		//myPaginatorFun("myPages", 1, 5);

		//ajax分页
		/* //分页回调函数,点击按钮事件
		myoptions.onPageClicked = function(event, originalEvent, type, page) {
			//ajax回调接收数据成功时再重新初始化分页按钮
			console.log("pageNo=", page);
		};
		//ajax回调接收数据成功时再重新初始化分页按钮
		function ajaxLoadData(data, url, page) {
			//TODO:
		}
		ajaxLoadData("", url, 1); */
		var pages;
		var pageNo;
		if ("${findusers.pages}" == 0) {
			pages = 1;
			pageNo = 1;
		} else {
			pages = "${findusers.pages}";
			pageNo = "${findusers.pageNum}";
		}

		//使用bootstrap分页插件实现分页功能
		var options = {
			bootstrapMajorVersion : 3,//表示当前bootstrap版本号
			currentPage : pageNo,//表示当前页
			totalPages : pages,//一共多少页
			size : "normal",
			pageUrl : function(type, page, current) {
				return "${pageContext.request.contextPath}${usersrc}"
						+ page;
			}
		};

		$("#myPages").bootstrapPaginator(options);

		//启用-禁用

		$("input[name='modifyStatus']")
				.click(
						function() {
							$.ajax({
										type : "post",
										url : "${pageContext.request.contextPath}/user/modifyStatus.do",
										data : {
											"id" : $(this).attr("data-id")
										},
										dataType : "json",
										success : function(result) {
											if (result.responseCode == "2001") {
												location.href = "${pageContext.request.contextPath}${usersrc}"
														+ pageNo;
											} else {
												$("#errorMsg")
														.tooltip(
																{
																	title : "error",
																	placement : "center",
																	template : "<div class='tooltip errorMsg'>"
																			+ result.message
																			+ "</div>",
																	tigger : "manual",
																}).tooltip(
																"show");
											}
										}
									});
						});


		$("input[name='showModify']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/user/findById.do",
    			data:{"id":$(this).attr("data-id")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == "2001"){
    					$("#user_id").val(result.returnObject.id);
    					$("#username").val(result.returnObject.nickname);
    					$("#roleName").val(result.returnObject.role);
    					$("#email").val(result.returnObject.email);
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			}
    		});
    	
    	});


		//实现修改功能
    	$("#modifyUser").click(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/user/modifyUser.do",
				data:{
					"id":$("#user_id").val(),
					"nickname":$("#username").val(),
					"role":$("#roleName").val(),
					"password":$("#password").val(),
					"email":$("#email").val()
				},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}${usersrc}"+pageNo;    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    				setTimeout(function(){
    						location.href="${pageContext.request.contextPath}${usersrc}"+pageNo;
    					},1000);
				}
				
			});    	
    	});

	$("#doSearch").click(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/user/searchUser.do",
				data:{
					"username":$("#user-name").val(),
					"nickname":$("#user-nickname").val(),
					"email":$("#user-email").val(),
					"role":$("#role-name").val(),
					"createBegin":$("#createBegin").val(),
					"createEnd":$("#createEnd").val(),
					"loginBegin":$("#loginBegin").val(),
					"loginEnd":$("#loginEnd").val()
				},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}/user/showSelect.do?pageNo=1";    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    				setTimeout(function(){
    						location.href="${pageContext.request.contextPath}${usersrc}"+pageNo;
    					},1000);
				}
				
			});    	
    	});










	});
</script>

</head>

<body>
	<div class="panel panel-default" id="userInfo">
		<div class="panel-heading">
			<h3 class="panel-title">
				用户管理&nbsp;&nbsp;&nbsp;<span id="errorMsg"></span>
			</h3>
		</div>
		<div>
			<input type="button" value="查询" class="btn btn-success" id="doSearch"
				style="margin: 5px 5px 5px 15px;" /> <input type="button"
				class="btn btn-primary" id="show-user-search" value="展开搜索" /> <input
				type="button" value="收起搜索" class="btn btn-primary"
				id="upp-user-search" style="display: none;" />
		</div>

		<div class="panel-body">
			<div class="showusersearch" style="display: none;">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label for="user-name" class="col-xs-3 control-label">登录名：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control" id="user-name"
									placeholder="请输入登录名" />
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label for="user-nickname" class="col-xs-3 control-label">昵称：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control" id="user-nickname"
									placeholder="请输入昵称" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label for="user-email" class="col-xs-3 control-label">邮箱：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control" id="user-email"
									placeholder="请输入邮箱" />
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label for="role-name" class="col-xs-3 control-label">角色：</label>
							<div class="col-xs-8">
								<select class="form-control" id="role-name" name="role-name">
									<option value="-1">全部</option>
									<option value="normal">普通</option>
									<option value="admin">管理员</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">开始日期：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control" id="createBegin"
									placeholder="请输入创建开始时间:2017-10-10" />
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">结束日期：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									placeholder="请输入创建结束时间:2017-10-12" id="createEnd"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">开始日期：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									placeholder="请输入登录开始时间:2017-10-10" id="loginBegin" />
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">结束日期：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									placeholder="请输入登录结束时间:2017-10-12" id="loginEnd" />
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="show-list">
				<table class="table table-bordered table-hover"
					style='text-align: center;'>
					<thead>
						<tr class="text-danger">
							<th class="text-center">编号</th>
							<th class="text-center">登录名</th>
							<th class="text-center">角色</th>
							<th class="text-center">昵称</th>
							<th class="text-center">邮箱</th>
							<th class="text-center">创建日期</th>
							<th class="text-center">最近登录日期</th>
							<th class="text-center">状态</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody id="tb">
						<c:forEach items="${findusers.list}" var="user">
							<tr>
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.role}</td>
								<td>${user.nickname}</td>
								<td>${user.email}</td>
								<td>${user.createDateStr}</td>
								<td>${user.loginDateStr}</td>
								<td><c:if test="${user.flag eq 0}">启用</c:if> <c:if
										test="${user.flag eq 1}">禁用</c:if></td>
								<td class="text-center"><input type="button"
									class="btn btn-warning btn-sm doModify" data-id="${user.id }"
									name="showModify" value="修改" /> <c:if
										test="${user.flag eq 1 }">
										<input type="button" class="btn btn-success btn-sm"
											name="modifyStatus" data-id="${user.id }" value="启用" />
									</c:if> <c:if test="${user.flag eq 0 }">
										<input type="button" class="btn btn-danger btn-sm"
											name="modifyStatus" data-id="${user.id }" value="禁用" />
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 分页 -->
			<div style="text-align: center;">
				<ul id="myPages"></ul>
			</div>

			<div class="modal fade" tabindex="-1" id="myModal">
				<!-- 窗口声明 -->
				<div class="modal-dialog modal-lg">
					<!-- 内容声明 -->
					<div class="modal-content">
						<!-- 头部、主体、脚注 -->
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">用户修改</h4>
						</div>
						<div class="modal-body text-center">
							<div class="row text-right">
								<label for="user_id" class="col-xs-4 control-label">编号：</label>
								<div class="col-xs-4">
									<input type="text" class="form-control" id="user_id"
										readonly="true" />
								</div>
							</div>
							<br>
							<div class="row text-right">
								<label for="username" class="col-xs-4 control-label">昵称：</label>
								<div class="col-xs-4">
									<input type="text" class="form-control" id="username" />
								</div>
							</div>
							<br>
							<div class="row text-right">
								<label for="roleName" class="col-xs-4 control-label">角色：</label>
								<div class="col-xs-4">
									<select class="form-control" id="roleName" name="roleName">
										<option value="-1">全部</option>
										<option value="normal">普通</option>
										<option value="admin">管理员</option>
									</select>
								</div>
							</div>
							<br>
							<div class="row text-right">
								<label for="password" class="col-xs-4 control-label">密码：</label>
								<div class="col-xs-4">
									<input type="text" class="form-control" id="password" />
								</div>
							</div>
							<br>
							<div class="row text-right">
								<label for="email" class="col-xs-4 control-label">邮箱：</label>
								<div class="col-xs-4">
									<input type="email" class="form-control" id="email" />
								</div>
							</div>
							<br>
						</div>
						<div class="modal-footer">
							<button class="btn btn-warning updateOne" id="modifyUser">修改</button>
							<button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>